package com.jingdong.wireless.mpaas.jdreport.jdreportprotocol;

import android.content.Context;
import android.util.Log;
import com.jingdong.wireless.mpaas.jdreport.handler.JdReportHandler;
import com.jingdong.wireless.mpaas.jdreport.handler.JdReportType;
import com.jingdong.wireless.mpaas.jdreport.report.JdReportManager;
import com.jingdong.wireless.mpaas.jdreport.util.CommonParams;
import java.util.Map;

public class JDReportModule{

    /**
     * 保存崩溃信息
     */
    public void saveCrashInfo(Map<String, Object> params) {
        saveCrashInfo(params);
    }

     public void saveCrashInfo(Map<String, Object> params, IJDReportListener listener, Object info) {
        saveInfo(JdReportType.CRASH, params, listener, info);
    }

    /**
     * 保存网络信息
     */
    public void saveNetInfo(Map<String, Object> params) {
        saveInfo(JdReportType.NET, params, null, null);
    }

    /**
     * 上报崩溃信息
     *
     * @param params
     */
    public void reportCrashInfo(Map<String, Object> params, IJDReportListener listener, Object info) {
        reportInfo(JdReportType.CRASH, params, listener, info);
    }

    /**
     * 上报网络信息
     *
     * @param params
     */
    public void reportNetInfo(Map<String, Object> params, IJDReportListener listener, Object info) {
        reportInfo(JdReportType.NET, params, listener, info);
    }

    public static void onCreate(Context context, boolean b) {
        Log.i(CommonParams.TAG, "JDReportModule.onCreate()");
        JdReportHandler.INSTANCE.init(context);
    }

    private void saveInfo(
            JdReportType type, Map<String, Object> params,
            IJDReportListener listener, Object info) {
        if (params != null && params.size() != 0) {
            Log.i(CommonParams.TAG, "JDReportModule.saveInfo()");
            JdReportHandler.INSTANCE.saveInfo(type, params, listener, info);
        }
    }

    private void reportInfo(JdReportType type, Map<String, Object> body,
                            IJDReportListener listener, Object info) {
        Log.i(CommonParams.TAG, "JDReportModule.reportInfo()");
        JdReportManager.INSTANCE.reportForOuter(type, body, listener, info);
    }
}
