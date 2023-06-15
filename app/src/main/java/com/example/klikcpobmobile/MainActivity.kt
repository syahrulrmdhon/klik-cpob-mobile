package com.example.klikcpobmobile


import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webview : WebView? = findViewById(R.id.web_login)
        val progressbar : ProgressBar? = findViewById(R.id.progressBar)
        progressbar?.max = 100
        webview?.settings?.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
        }
        webview?.webViewClient = object: WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                progressbar?.visibility = View.VISIBLE;
                return false
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                //hide progressbar
                progressbar?.visibility = View.GONE;
            }
        }
        webview?.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                setProgress(progress * 100) //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                if (progress == 80) {
                    progressbar?.visibility = View.GONE;
                }
            }
        }
        webview?.loadUrl("https://klikcpob.pom.go.id")
    }
}