package com.example.androidbridgetest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.androidbridgetest.databinding.ActivityMainBinding
import android.webkit.WebView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mWebView: WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mWebView = binding.webView

        mWebView.webViewClient = WebViewClient()
        mWebView.webChromeClient = WebChromeClient()
        mWebView.settings.javaScriptEnabled = true

        mWebView.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                val permission = ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.CAMERA)
                if (permission == PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        request.grant(request.resources)
                    }
                } else {
                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.CAMERA), 99)
                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 99)
                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 99)
                }
            }
        }


        val data = String


        mWebView.addJavascriptInterface(AndroidBridge(), "Android")
        mWebView.loadUrl("https://kyc-dev.rootone.com/")
        mWebView.loadData(data.toString(),"text/html; charset=utf-8", "UTF-8" )


        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            // Android 4.0 미만 버전
            mWebView.loadData(data.toString(), "text/html", "UTF-8")
        } else {
            // Android 4.1 이상 버전
            mWebView.loadData(data.toString(),"text/html; charset=utf-8", "UTF-8")
        }

    }
}

