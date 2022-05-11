package com.jingdong.wireless.mpaas.jdreport.jdreportprotocol;

import android.content.Context;
import android.util.Log;

//import com.jingdong.wireless.mpaas.jdreport.entity.JDReportStrategy;
import com.jingdong.wireless.mpaas.jdreport.handler.JdReportHandler;
import com.jingdong.wireless.mpaas.jdreport.handler.JdReportType;
import com.jingdong.wireless.mpaas.jdreport.report.JdReportManager;
import com.jingdong.wireless.mpaas.jdreport.util.CommonParams;
import java.util.Map;

public class JDReportModule implements IJDReportProtocol {

    @Override
    public void saveCrashInfo(Map<String, Object> params) {
        saveCrashInfo(params);
    }

    @Override
    public void saveCrashInfo(Map<String, Object> params, IJDReportListener listener, Object info) {
        saveInfo(JdReportType.CRASH, params, listener, info);
    }

    @Override
    public void saveNetInfo(Map<String, Object> params) {
        saveInfo(JdReportType.NET, params, null, null);
    }

    @Override
    public void reportCrashInfo(Map<String, Object> params, IJDReportListener listener, Object info) {
        reportInfo(JdReportType.CRASH, params, listener, info);
    }

    @Override
    public void reportNetInfo(Map<String, Object> params, IJDReportListener listener, Object info) {
        reportInfo(JdReportType.NET, params, listener, info);
    }

//    @Override
//    public JDReportStrategy getReportStrategy() {
//        return JdReportHandler.INSTANCE.getCollectionConfig();
//    }

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
