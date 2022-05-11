package com.jingdong.wireless.mpaas.jdreport.report

import android.util.Log
import com.google.gson.Gson
import com.jingdong.wireless.mpaas.jdreport.entity.EntityBody
import com.jingdong.wireless.mpaas.jdreport.entity.JdReportBaseEntity
import com.jingdong.wireless.mpaas.jdreport.handler.JdReportHandler
import com.jingdong.wireless.mpaas.jdreport.handler.JdReportType
import com.jingdong.wireless.mpaas.jdreport.jdreportprotocol.IJDReportListener
import com.jingdong.wireless.mpaas.jdreport.util.CommonParams

object JdReportManager {
    private val gson = Gson()

    /**
     * 上报方法
     */
    internal fun reportByType(
        type: JdReportType, listener: IJDReportListener? = null,
        info: Any? = null
    ) {
        // 判断当前网络环境是否满足下发的配置
        if ("all" == JdReportHandler.getStrategyByType(type)?.reportNet) {
            val maxLogCount = JdReportHandler.getDefaultStrategyByType(type).maxLogCount
            val pair = getRequestBean(type, maxLogCount)
            if (pair.second.size == 0) {
                Log.e(CommonParams.TAG, "${type.name} 类型下没有缓存数据")
                return
            }

            JdRequest.reportInfo(pair.first, type, object : IJDReportListener {
                override fun onStart(p0: Any?) {
                    listener?.onStart(p0)
                }

                override fun onEnd(code: Int, msg: String?, info: Any?) {
                    if (code == 0) {
                        // 上报成功，删除本地配置，
                        JdReportHandler.removePerformanceInfo(type, pair.second)
                    }
                    listener?.onEnd(code, msg, info)
                }

                override fun onError(code: Int, msg: String?, info: Any?) {
                    listener?.onError(code, msg, info)
                }

            }, info)
        }
    }

    fun reportForOuter(
        type: JdReportType,
        body: Map<String, Any?>,
        ijdReportListener: IJDReportListener?,
        params: Any?
    ) {
        // 判断当前网络环境是否满足下发的配置
        if ("all" == JdReportHandler.getStrategyByType(type)?.reportNet) {
            val jdReportBaseEntity = JdReportBaseEntity.createEntity()
            val valueList = mutableListOf<Any>()
            valueList.add(body)

            jdReportBaseEntity.body = gson.toJson(EntityBody(valueList))
            JdRequest.reportInfo(jdReportBaseEntity, type, ijdReportListener, params)
        }
    }

    private fun getRequestBean(
        type: JdReportType,
        maxLogCount: Int
    ): Pair<JdReportBaseEntity, MutableList<String>> {
        val jdReportBaseEntity = JdReportBaseEntity.createEntity()
        val keys = JdReportHandler.getAllPerformanceInfoKey(type)
        val keyList = mutableListOf<String>()
        val valueList = mutableListOf<Any>()
        val count = if (keys?.size ?: 0 >= maxLogCount) maxLogCount else keys?.size ?: 0
        for (i in 0 until count) {
            keys?.get(i)?.let {
                keyList.add(it)
                JdReportHandler.getPerformanceInfo(it, type)
                    ?.let { it1 -> valueList.add(it1) }
            }
        }
        jdReportBaseEntity.body = gson.toJson(EntityBody(valueList))
        return Pair(jdReportBaseEntity, keyList)
    }
}