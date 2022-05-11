package com.jingdong.wireless.mpaas.jdreport.entity;

/**
 * Author: xuweiyu
 * Date: 2021/11/17
 * Email: xuweiyu1@jd.com
 * Description:
 */
public class JDReportStrategy {
    // 异常上报策略配置
    private Strategy crash;
    // 网络请求策略配置
    private Strategy request;

    public JDReportStrategy() {
    }

    public JDReportStrategy(Strategy crash, Strategy request) {
        this.crash = crash;
        this.request = request;
    }

    public Strategy getCrash() {
        return crash;
    }

    public void setCrash(Strategy crash) {
        this.crash = crash;
    }

    public Strategy getRequest() {
        return request;
    }

    public void setRequest(Strategy request) {
        this.request = request;
    }
}

