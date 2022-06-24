package com.example.androidbridgetest


import android.os.Handler
import android.webkit.JavascriptInterface


class AndroidBridge() {

    private lateinit var mHandler: Handler



    @JavascriptInterface
    fun requestData(data : String) {

    }


}