package com.jingdong.wireless.mpaas.jdreport.entity;

public class Strategy {
    //数据上报开关1开0关
    private int enable = 1;
    // 批量上报最大条数
    private int maxLogCount = 30;
    // 上报网络类型, all:表示全网，不同的网络用逗号分隔比如"2g,3g,4g,5g,wifi"
    private String reportNet;
    // 上报采样率默认100%
    private int sample;

    public Strategy() {
    }

    public Strategy(int enable, int maxLogCount, String reportNet, int sample) {
        this.enable = enable;
        this.maxLogCount = maxLogCount;
        this.reportNet = reportNet;
        this.sample = sample;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getMaxLogCount() {
        return maxLogCount;
    }

    public void setMaxLogCount(int maxLogCount) {
        this.maxLogCount = maxLogCount;
    }

    public String getReportNet() {
        return reportNet;
    }

    public void setReportNet(String reportNet) {
        this.reportNet = reportNet;
    }

    public int getSample() {
        return sample;
    }

    public void setSample(int sample) {
        this.sample = sample;
    }
}
