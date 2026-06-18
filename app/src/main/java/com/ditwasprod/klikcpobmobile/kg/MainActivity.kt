package com.ditwasprod.klikcpobmobile.kg

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import android.content.res.Configuration

class MainActivity : AppCompatActivity() {

    override fun onBackPressed() {
        val webview: WebView? = findViewById(R.id.web_login)
        val progressbar: ProgressBar? = findViewById(R.id.progressBar)

        if (webview?.canGoBack() == true) {
            webview.goBack()
            progressbar?.visibility = View.VISIBLE

            webview.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView, progress: Int) {
                    setProgress(progress * 100)

                    if (progress >= 80) {
                        progressbar?.visibility = View.GONE
                    }
                }
            }
        } else {
            finishAffinity()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webview: WebView? = findViewById(R.id.web_login)
        val progressbar: ProgressBar? = findViewById(R.id.progressBar)

        progressbar?.max = 100

        webview?.settings?.apply {
            javaScriptEnabled = true
            domStorageEnabled = true

            builtInZoomControls = true
            displayZoomControls = false
            setSupportZoom(true)

            loadWithOverviewMode = true
            useWideViewPort = true

            allowFileAccess = true
            allowContentAccess = true

            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        webview?.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {

                val url = request?.url.toString()

                if (url.lowercase().endsWith(".pdf")) {

                    val pdfViewerUrl =
                        "https://docs.google.com/gview?embedded=true&url=$url"

                    view?.loadUrl(pdfViewerUrl)

                    return true
                }

                progressbar?.visibility = View.VISIBLE
                return false
            }
            override fun onPageFinished(
                view: WebView?,
                url: String?
            ) {
                super.onPageFinished(view, url)
                progressbar?.visibility = View.GONE
            }

            override fun onReceivedError(
                view: WebView?,
                errorCode: Int,
                description: String?,
                failingUrl: String?
            ) {
                super.onReceivedError(
                    view,
                    errorCode,
                    description,
                    failingUrl
                )

                Log.e(
                    "WEBVIEW",
                    "Error: $description URL:$failingUrl"
                )
            }
        }

        webview?.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(
                view: WebView,
                progress: Int
            ) {
                setProgress(progress * 100)

                if (progress >= 80) {
                    progressbar?.visibility = View.GONE
                }
            }
        }

        webview?.loadUrl("https://klikcpob.pom.go.id")
    }
}