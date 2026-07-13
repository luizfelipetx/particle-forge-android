package com.consultanomesujo.particleforge

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var adView: AdView
    private var interstitial: InterstitialAd? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.gameWebView)
        adView = findViewById(R.id.adView)
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            databaseEnabled = true
            allowFileAccess = true
            allowContentAccess = true
            mediaPlaybackRequiresUserGesture = false
            builtInZoomControls = false
            displayZoomControls = false
            useWideViewPort = true
            loadWithOverviewMode = true
        }
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.addJavascriptInterface(GameBridge(), "AndroidAds")
        webView.loadUrl("file:///android_asset/www/index.html")

        findViewById<android.view.View>(R.id.aboutButton).setOnClickListener { showAbout() }
        MobileAds.initialize(this) {
            adView.loadAd(AdRequest.Builder().build())
            loadInterstitial()
        }
    }

    private fun loadInterstitial() {
        InterstitialAd.load(
            this,
            getString(R.string.admob_interstitial_id),
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) { interstitial = ad }
            }
        )
    }

    inner class GameBridge {
        @JavascriptInterface
        fun showInterstitial() {
            runOnUiThread {
                val ad = interstitial
                if (ad != null) {
                    interstitial = null
                    ad.show(this@MainActivity)
                    loadInterstitial()
                }
            }
        }
    }

    private fun showAbout() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.app_name))
            .setMessage(getString(R.string.about_text))
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack() else super.onBackPressed()
    }

    override fun onPause() { webView.onPause(); super.onPause() }
    override fun onResume() { super.onResume(); webView.onResume() }
    override fun onDestroy() { adView.destroy(); webView.destroy(); super.onDestroy() }
}
