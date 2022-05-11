package com.jingdong.wireless.mpaas.jdreport.jdreportprotocol;

public interface IJDReportListener {
    void onStart(Object info);

    void onEnd(int code, String msg, Object info);

    void onError(int code, String msg, Object info);
}
