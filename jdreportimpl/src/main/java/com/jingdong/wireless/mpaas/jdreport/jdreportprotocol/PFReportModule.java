package com.jingdong.wireless.mpaas.jdreport.jdreportprotocol;

import android.content.Context;
import android.util.Log;
import com.jingdong.wireless.mpaas.jdreport.handler.PFReportHandler;
import com.jingdong.wireless.mpaas.jdreport.handler.PFReportType;
import com.jingdong.wireless.mpaas.jdreport.report.PFReportManager;
import com.jingdong.wireless.mpaas.jdreport.util.CommonParams;

public class PFReportModule {

    /**
     * 保存崩溃信息
     */
    public void saveCrashInfo(String params) {
        saveCrashInfo(params);
    }

     public void saveCrashInfo(String params, IPFReportListener listener) {
        saveInfo(PFReportType.CRASH, params, listener);
    }

    /**
     * 保存网络信息
     */
    public void saveNetInfo(String params) {
        saveInfo(PFReportType.NET, params, null);
    }

    /**
     * 上报崩溃信息
     *
     * @param params
     */
    public void reportCrashInfo(String params, IPFReportListener listener) {
        reportInfo(PFReportType.CRASH, params, listener);
    }

    /**
     * 上报网络信息
     *
     * @param params
     */
    public void reportNetInfo(String params, IPFReportListener listener) {
        reportInfo(PFReportType.NET, params, listener);
    }

    public static void onCreate(Context context, boolean b) {
        Log.i(CommonParams.TAG, "JDReportModule.onCreate()");
        PFReportHandler.INSTANCE.init(context);
    }

    private void saveInfo(
            PFReportType type, String params,
            IPFReportListener listener) {
        if (params != null) {
            Log.i(CommonParams.TAG, "JDReportModule.saveInfo()");
            PFReportHandler.INSTANCE.saveInfo(type, params, listener);
        }
    }

    private void reportInfo(PFReportType type, String body,
                            IPFReportListener listener) {
        Log.i(CommonParams.TAG, "JDReportModule.reportInfo()");
        PFReportManager.INSTANCE.reportForOuter(type, body, listener);
    }
}
