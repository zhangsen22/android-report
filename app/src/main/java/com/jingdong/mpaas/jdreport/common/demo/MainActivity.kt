package com.jingdong.mpaas.jdreport.common.demo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.jd.mpaas.jdreport.common.demo.R
import com.jingdong.wireless.mpaas.jdreport.jdreportprotocol.PFReportModule

class MainActivity : AppCompatActivity() {

    val crashInfo =
        "{\"issueId\":\"\${System.currentTimeMillis()\",\"stack\":\"0      android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3270)\\n1      android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3409)\\n2      android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:83)\\n3      android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:135)\\n4      android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:95)\\n5      android.app.ActivityThread\$H.handleMessage(ActivityThread.java:2016)\\n6      android.os.Handler.dispatchMessage(Handler.java:107)\\n7      android.os.Looper.loop(Looper.java:214)\\n8      android.app.ActivityThread.main(ActivityThread.java:7356)\\n9      java.lang.reflect.Method.invoke(Native Method)\\n10     com.android.internal.os.RuntimeInit\$MethodAndArgsCaller.run(RuntimeInit.java:492)\\n11     com.android.internal.os.ZygoteInit.main(ZygoteInit.java:930)\\n\",\"memory\":536870912,\"cpuArch\":\"x86_64,x86,\",\"storage\":6240665600,\"usedmem\":536870912,\"rom\":\"OTHER_ROM\",\"remainmem\":4769656,\"connmode\":1," +
                "\"curTime\":${System.currentTimeMillis()},\"background\":\"1\",\"root\":\"0\",\"digest\":\"Unable to start activity ComponentInfo{com.jd.mpaas.impl.jdcrashmodule.demo/com.jd.mpaas.impl.jdcrashmodule.demo.MainActivity}: java.lang.ArithmeticException: divide by zero\",\"appKey\":\"gulq2lcq0nomhdvo\",\"busiType\":\"java\",\"remainsto\":4716331008,\"power\":100}"

    val netInfo =
        "{\"cost\":\"167\",\"code\":\"200\",\"function\":\"/iosNews\",\"domain\":\"api.3g.ifeng.com\",\"appKey\":\"gulq2lcq0nomhdvo\",\"time\":\"1638345663649\"}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gson = Gson()

        findViewById<TextView>(R.id.crash_click).setOnClickListener {

//            for (index in 1..5) {
                PFReportModule()
                    .saveCrashInfo(crashInfo, null)
                PFReportModule()
                    .saveNetInfo(netInfo)
//            }
        }

//        val crashInfos: HashMap<String, Any> =
//            gson.fromJson(crashInfo, HashMap::class.java) as HashMap<String, Any>
//        //jdReportModule.saveCrashInfo(crashInfos)
//        //jdReportModule.reportCrashInfo(crashInfos, "0.0.1", null, null)
//        val netInfos = gson.fromJson(netInfo, HashMap::class.java) as HashMap<String, Any>
//        jdReportModule.saveNetInfo(netInfos, "0.0.1")

    }
}
