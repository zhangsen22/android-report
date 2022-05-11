package com.jingdong.wireless.mpaas.jdreport.util
import com.jingdong.wireless.mpaas.jdreport.entity.Strategy
//import com.jingdong.wireless.mpaas.jdreport.handler.JdReportType
import com.jingdong.wireless.mpaas.jdreportimpl.BuildConfig

object CommonParams {
    const val TAG = "JDReportModule"
    const val SDK_VERSION = BuildConfig.sdkVersion
    private val jdReportStrategy = Strategy(1, 30, "all", 100)

    /**
     * 获取默认上报策略
     */
    fun getDefaultReportStrategy(): Strategy {
        return jdReportStrategy
    }
}