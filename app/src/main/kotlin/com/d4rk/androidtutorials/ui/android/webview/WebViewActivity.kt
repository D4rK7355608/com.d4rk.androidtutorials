package com.d4rk.androidtutorials.ui.android.webview
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityWebviewBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class WebViewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWebviewBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val webView: WebView = binding.webView
        webView.loadUrl("https://sites.google.com/view/d4rk7355608/home")
        webView.settings.javaScriptEnabled = true
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity( Intent(this, WebViewCodeActivity::class.java))
        }
        FastScrollerBuilder(binding.webView).useMd2Style().build()
    }
}