package com.jingdong.wireless.mpaas.jdreport.jdreportprotocol;

import android.content.Context;
import android.util.Log;
import com.jingdong.wireless.mpaas.jdreport.handler.JdReportHandler;
import com.jingdong.wireless.mpaas.jdreport.handler.JdReportType;
import com.jingdong.wireless.mpaas.jdreport.report.JdReportManager;
import com.jingdong.wireless.mpaas.jdreport.util.CommonParams;

public class JDReportModule{

    /**
     * 保存崩溃信息
     */
    public void saveCrashInfo(String params) {
        saveCrashInfo(params);
    }

     public void saveCrashInfo(String params, IJDReportListener listener) {
        saveInfo(JdReportType.CRASH, params, listener);
    }

    /**
     * 保存网络信息
     */
    public void saveNetInfo(String params) {
        saveInfo(JdReportType.NET, params, null);
    }

    /**
     * 上报崩溃信息
     *
     * @param params
     */
    public void reportCrashInfo(String params, IJDReportListener listener) {
        reportInfo(JdReportType.CRASH, params, listener);
    }

    /**
     * 上报网络信息
     *
     * @param params
     */
    public void reportNetInfo(String params, IJDReportListener listener) {
        reportInfo(JdReportType.NET, params, listener);
    }

    public static void onCreate(Context context, boolean b) {
        Log.i(CommonParams.TAG, "JDReportModule.onCreate()");
        JdReportHandler.INSTANCE.init(context);
    }

    private void saveInfo(
            JdReportType type, String params,
            IJDReportListener listener) {
        if (params != null) {
            Log.i(CommonParams.TAG, "JDReportModule.saveInfo()");
            JdReportHandler.INSTANCE.saveInfo(type, params, listener);
        }
    }

    private void reportInfo(JdReportType type, String body,
                            IJDReportListener listener) {
        Log.i(CommonParams.TAG, "JDReportModule.reportInfo()");
        JdReportManager.INSTANCE.reportForOuter(type, body, listener);
    }
}
