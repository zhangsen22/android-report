package com.jingdong.wireless.mpaas.jdreport.entity

import com.jingdong.wireless.mpaas.jdreport.util.CommonParams

/**
 * Author: xuweiyu
 * Date: 2021/9/8
 * Email: xuweiyu1@jd.com
 * Description: 性能数据类基类
 */
internal data class JdReportBaseEntity(
    val client: String = "android",
    var clientVersion: String = "",
    var build: String = "", //buildCode 客户端小版本号;
    var uuid: String = "", //唯一设备标识
    var osVersion: String = "", //唯一设备标识
    var screen: String = "",
    var networkType: String = "",
    var partner: String = "",
    var d_brand: String = "",
    var d_model: String = "",
    var t: String = "", //
    var sdkVersion: String = "",
    var body: String = ""
) {

    companion object {

        @JvmStatic
        fun createEntity(sdkVersion: String = CommonParams.SDK_VERSION): JdReportBaseEntity {
            return JdReportBaseEntity(
//                clientVersion = BaseInfo.getAppVersionName(),
//                build = BaseInfo.getAppVersionCode().toString(),
//                uuid = JDConfig.getInstance().uuid,
//                osVersion = BaseInfo.getAndroidVersion(),
//                screen = BaseInfo.getScreenHeight().toString() + "*" + BaseInfo.getScreenWidth(),
//                networkType = BaseInfo.getNetworkType(),
//                partner = JDConfig.getInstance().appPartner,
//                d_brand = BaseInfo.getDeviceBrand(),
//                d_model = BaseInfo.getDeviceModel(),
                t = System.currentTimeMillis().toString(),
                sdkVersion = sdkVersion
            )
        }
    }
}

internal data class EntityBody(
    var msg: MutableList<Any>? = null
)