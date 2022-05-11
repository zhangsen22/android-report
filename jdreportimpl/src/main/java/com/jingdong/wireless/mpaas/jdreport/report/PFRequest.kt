package com.jingdong.wireless.mpaas.jdreport.report

import android.util.Log
import com.jingdong.wireless.mpaas.jdreport.handler.PFReportType
import com.jingdong.wireless.mpaas.jdreport.jdreportprotocol.IPFReportListener
import com.jingdong.wireless.mpaas.jdreport.util.CommonParams.TAG
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal object PFRequest {


    @Volatile
    private var uploadIngCrash = false

    @Volatile
    private var uploadIngNet = false

    @Synchronized
    private fun isCanUpload(type: PFReportType): Boolean {
        val isUploadIng = when (type) {
            PFReportType.CRASH -> uploadIngCrash
//            JdReportType.EXCEPTION -> uploadIngException
//            JdReportType.PAGE -> uploadIngPage
            PFReportType.NET -> uploadIngNet
//            JdReportType.START -> uploadIngStart
        }

        return if (isUploadIng) {
            !isUploadIng
        } else {
            setUploadIngStatue(type, true)
            !isUploadIng
        }
    }

    private fun setUploadIngStatue(type: PFReportType, status: Boolean) {
        when (type) {
            PFReportType.CRASH -> uploadIngCrash = status
//            JdReportType.EXCEPTION -> uploadIngException = status
//            JdReportType.PAGE -> uploadIngPage = status
            PFReportType.NET -> uploadIngNet = status
//            JdReportType.START -> uploadIngStart = status
        }
    }

    private fun setUploadIngFalse(type: PFReportType) {
        setUploadIngStatue(type, false)
    }

    /**
     * 上报信息 接口
     */
    fun reportInfo(
        data: String,
        type: PFReportType,
        listener: IPFReportListener?
    ) {
        Log.e(
            TAG, "准备上报数据 \n " +
                    //"body:${jdPerformanceBaseEntity.body} \n" +
                    //"url:${url} \n" +
                    "数据:${data} \n" +
                    "类型：${type}"
        )

//        request.requestCallback = object :
//            RequestCallBack {
//            override fun onSuccess(response: String?) {
//                Log.e(TAG, "上报数据-->response:$response")
//                if (TextUtils.isEmpty(response)) {
//                    listener?.onEnd(-1, "上报接口请求失败", null)
//                } else {
//                    val bean = JSONObject(response)
//                    if (bean.getInt("code") == 200) {
//                        Log.e(TAG, "上报数据-->onSuccess")
//                        listener?.onEnd(0, "上报成功", params)
//                        setUploadIngFalse(type)
//                    } else {
//                        setUploadIngFalse(type)
//                        Log.e(
//                            TAG,
//                            "上报数据-->onFail:${bean.getString("message")}"
//                        )
//                        listener?.onEnd(bean.getInt("code"), bean.getString("message"), params)
//                    }
//                }
//            }
//
//            override fun onError(errorMsg: String?) {
//                Log.e(TAG, "上报数据-->onError:$errorMsg")
//                setUploadIngFalse(type)
//                listener?.onEnd(-1, errorMsg, params)
//            }
//        }

        GlobalScope.launch {
            Log.e(TAG, "开始上报数据")
            listener?.onStart(null)
//            request.execute()
        }
    }
}