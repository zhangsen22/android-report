package com.jingdong.wireless.mpaas.jdreport.report

import android.util.Log
import com.google.gson.Gson
import com.jingdong.wireless.mpaas.jdreport.handler.PFReportHandler
import com.jingdong.wireless.mpaas.jdreport.handler.PFReportType
import com.jingdong.wireless.mpaas.jdreport.jdreportprotocol.IPFReportListener
import com.jingdong.wireless.mpaas.jdreport.util.CommonParams

object PFReportManager {
    private val gson = Gson()

    /**
     * 上报方法
     */
    internal fun reportByType(
        type: PFReportType, listener: IPFReportListener? = null
    ) {
        // 判断当前网络环境是否满足下发的配置
        if ("all" == PFReportHandler.getDefaultStrategyByType(type)?.reportNet) {
            val maxLogCount = PFReportHandler.getDefaultStrategyByType(type).maxLogCount
            val pair = getRequestBean(type, maxLogCount)
            if (pair.second.size == 0) {
                Log.e(CommonParams.TAG, "${type.name} 类型下没有缓存数据")
                return
            }

            PFRequest.reportInfo(pair.first, type, object :
                IPFReportListener {
                override fun onStart(p0: Any?) {
                    listener?.onStart(p0)
                }

                override fun onEnd(code: Int, msg: String?, info: Any?) {
                    if (code == 0) {
                        // 上报成功，删除本地配置，
                        PFReportHandler.removePerformanceInfo(type, pair.second)
                    }
                    listener?.onEnd(code, msg, info)
                }

                override fun onError(code: Int, msg: String?, info: Any?) {
                    listener?.onError(code, msg, info)
                }

            })
        }
    }

    fun reportForOuter(
        type: PFReportType,
        body: String?,
        IPFReportListener: IPFReportListener?
    ) {
        // 判断当前网络环境是否满足下发的配置
        if ("all" == PFReportHandler.getDefaultStrategyByType(type)?.reportNet) {
            if (body != null) {
                PFRequest.reportInfo(body, type, IPFReportListener)
            }
        }
    }

    private fun getRequestBean(
        type: PFReportType,
        maxLogCount: Int
    ): Pair<String, MutableList<String>> {
        val keys = PFReportHandler.getAllPerformanceInfoKey(type)
        val keyList = mutableListOf<String>()
        val valueList = mutableListOf<Any>()
        val count = if (keys?.size ?: 0 >= maxLogCount) maxLogCount else keys?.size ?: 0
        for (i in 0 until count) {
            keys?.get(i)?.let {
                keyList.add(it)
                PFReportHandler.getPerformanceInfo(it, type)
                    ?.let {
                            it1 -> valueList.add(it1)
                    }
            }
        }
        return Pair(gson.toJson(valueList), keyList)
    }
}