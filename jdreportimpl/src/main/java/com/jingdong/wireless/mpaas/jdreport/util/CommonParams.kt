package com.jingdong.wireless.mpaas.jdreport.util

import com.jingdong.wireless.mpaas.jdreport.entity.JDReportStrategy
import com.jingdong.wireless.mpaas.jdreport.entity.Strategy
import com.jingdong.wireless.mpaas.jdreport.handler.JdReportType
import com.jingdong.wireless.mpaas.jdreportimpl.BuildConfig

/**
 * Author: xuweiyu
 * Date: 2021/11/9
 * Email: xuweiyu1@jd.com
 * Description:
 */
object CommonParams {
    const val TAG = "JDReportModule"
    const val SDK_VERSION = BuildConfig.sdkVersion
    private val jdReportStrategy = JDReportStrategy(
        Strategy(1, 30, "all", 100),
        Strategy(1, 30, "all", 100)
    )

    /**
     * 获取默认上报策略
     */
    fun getDefaultReportStrategy(): JDReportStrategy {
        return jdReportStrategy
    }
}