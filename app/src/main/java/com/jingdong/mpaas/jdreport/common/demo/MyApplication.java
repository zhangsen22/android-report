package com.jingdong.mpaas.jdreport.common.demo;

import android.app.Application;

import com.jingdong.wireless.mpaas.jdreport.jdreportprotocol.JDReportModule;


/**
 * Author: xuweiyu
 * Date: 2021/11/8
 * Email: xuweiyu1@jd.com
 * Description:
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JDReportModule.onCreate(this,true);
    }
}
