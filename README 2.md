## JDReportModule
性能日志上报组件


## 引入
添加maven仓库
```
    maven { url 'http://artifactory.jd.com/libs-snapshots-local/' }
    maven { url 'http://artifactory.jd.com/libs-releases-local/' }
```

添加依赖
```
    implementation "com.jd.mpaas:jd-report:0.0.1-SNAPSHOT"
    implementation "com.jd.mpaas:jd-report-protocol:0.0.1-SNAPSHOT"
```

初始化代码
```
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 设置隐私协议是否同意的回调 不设置会影响性能数据上报
        JDPrivacyCheckHelper.getInstance().setPrivacyCheck(() -> true);
        // config 组件初始化
        JDConfig.getInstance().init(this, BuildConfig.DEBUG);
        JDStartUpLunch.getInstance().init(this, BuildConfig.DEBUG);
    }
}
```