package com.jingdong.wireless.mpaas.jdreport.handler

import android.content.Context
import android.os.SystemClock
import android.util.Log
import com.google.gson.Gson
import com.jingdong.wireless.mpaas.jdreport.report.JdReportManager
import com.jingdong.wireless.mpaas.jdreport.util.CommonParams
import com.jingdong.wireless.mpaas.jdreport.entity.Strategy
import com.jingdong.wireless.mpaas.jdreport.jdreportprotocol.IJDReportListener
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object JdReportHandler {

    // 本地mmkv保存拥有本地缓存数据的type类型的set
    private const val TYPE_SET_KEY = "TYPE_SET_KEY"

    private val configMMKV: MMKV by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        MMKV.mmkvWithID("JD_PERFORMANCE_CONFIG", MMKV.MULTI_PROCESS_MODE)
    }

    private val mMkvMap = mutableMapOf<JdReportType, MMKV>()

    @Volatile
    private var isInit = false

    @Synchronized
    private fun getMMKV(type: JdReportType): MMKV {
        return mMkvMap.getOrPut(type) {
            putTypeByName(type.name)
            MMKV.mmkvWithID(type.name.toUpperCase(), MMKV.MULTI_PROCESS_MODE)
        }
    }

    private val gson = Gson()

    fun saveInfo(
        type: JdReportType, params: String?, listener: IJDReportListener? = null
    ) {
        if (!isInit) {
            Log.e(CommonParams.TAG, "JDReportModule has not init")
            return
        }
        GlobalScope.launch {
            Log.e(
                CommonParams.TAG,
                "保存数据:${params} \n"
                        + "类型：${type}  " + "当前线程：${Thread.currentThread().name}"
            )
            // 判断配置中是否需要上报当前类型的数据
            if (getDefaultStrategyByType(type)?.enable == 1) {
                getMMKV(type).encode(
                    type.name + SystemClock.currentThreadTimeMillis(),
                    params
                )
            }
            JdReportManager.reportByType(type, listener)
        }
    }

    /**
     * 上报所有的缓存信息
     */
    fun uploadCache() {
        // 启动的时候上报所有已经存储的所有信息
        getHandleTypes().forEach {
            JdReportManager.reportByType(it)
        }
    }

    /**
     * 保存本地已经保存过数据的类型
     */
    private fun putTypeByName(type: String) {
        val set: HashSet<String> =
            configMMKV.decodeStringSet(TYPE_SET_KEY, mutableSetOf()) as HashSet<String>
        set.add(type)
        configMMKV.encode(TYPE_SET_KEY, set)
    }

    /**
     * 获取本地已经保存过数据的类型
     */
    private fun getHandleTypes(): List<JdReportType> {
        val set: HashSet<String> =
            configMMKV.decodeStringSet(TYPE_SET_KEY, mutableSetOf()) as HashSet<String>
        val handlerTypeList = mutableListOf<JdReportType>()
        set.forEach {
            handlerTypeList.add(JdReportType.valueOf(it))
        }
        return handlerTypeList
    }


    /**
     * 获取所有key
     */
    internal fun getAllPerformanceInfoKey(type: JdReportType): Array<String>? {
        return getMMKV(type).allKeys()
    }

    /**
     * 根据key删除value
     */
    internal fun removePerformanceInfo(type: JdReportType, keys: MutableList<String>) {
        getMMKV(type).removeValuesForKeys(keys.toTypedArray())
    }

    fun init(context: Context) {
        if (isInit) {
            Log.i(CommonParams.TAG, "JDReportModule already has init")
            return
        }
        MMKV.initialize(context)
        isInit = true
    }

    internal fun getPerformanceInfo(name: String, type: JdReportType): HashMap<*, *>? {
        val body = getMMKV(type).getString(name, null)
        return gson.fromJson(body, HashMap::class.java)
    }


    internal fun getDefaultStrategyByType(type: JdReportType): Strategy {
        return when (type) {
            JdReportType.CRASH -> CommonParams.getDefaultReportStrategy()
            JdReportType.NET -> CommonParams.getDefaultReportStrategy()
        }
    }
}