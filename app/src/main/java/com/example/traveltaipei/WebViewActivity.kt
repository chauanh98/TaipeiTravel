package com.example.traveltaipei

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.traveltaipei.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)
        binding.lifecycleOwner = this

        val url = intent.getStringExtra("url")
        url?.let {
            this.url = url
        }

        setupWebView()
    }

    private fun setupWebView() {
        url.let {
            binding.webView.apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(it)
            }
        }
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}