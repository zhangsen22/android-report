package com.jingdong.mpaas.jdreport.common.demo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.jd.mpaas.jdreport.common.demo.R
import com.jingdong.wireless.mpaas.jdreport.jdreportprotocol.PFReportModule

class MainActivity : AppCompatActivity() {

    val crashInfo =
        "{\"issueId\":\"\${System.currentTimeMillis()\",\"stack\":\"0 " + "\"curTime\":${System.currentTimeMillis()}}"

//    val netInfo =
//        "{\"cost\":\"167\",\"code\":\"200\",\"function\":\"/iosNews\",\"domain\":\"api.3g.ifeng.com\",\"appKey\":\"gulq2lcq0nomhdvo\",\"time\":\"1638345663649\"}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gson = Gson()

        findViewById<TextView>(R.id.crash_click).setOnClickListener {

//            for (index in 1..5) {
                PFReportModule()
                    .saveCrashInfo(crashInfo, null)
//                PFReportModule()
//                    .saveNetInfo(netInfo)
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
