package com.jingdong.wireless.mpaas.jdreport.jdreportprotocol;

//import com.jingdong.wireless.mpaas.jdreport.entity.JDReportStrategy;

import java.util.Map;

public interface IJDReportProtocol{

    /**
     * 保存崩溃信息
     */
    void saveCrashInfo(Map<String, Object> params);

    void saveCrashInfo(Map<String, Object> params, IJDReportListener listener, Object info);

    /**
     * 保存网络信息
     */
    void saveNetInfo(Map<String, Object> params);

    /**
     * 上报崩溃信息
     *
     * @param params
     */
    void reportCrashInfo(Map<String, Object> params, IJDReportListener listener, Object info);

    /**
     * 上报网络信息
     *
     * @param params
     */
    void reportNetInfo(Map<String, Object> params, IJDReportListener listener, Object info);

//
//    /**
//     * 获取上报策略
//     */
//    JDReportStrategy getReportStrategy();
}
