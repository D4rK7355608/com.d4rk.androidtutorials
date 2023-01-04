package com.d4rk.androidtutorials.ui.android.webview
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityWebviewBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupWebView()
        setupFloatingButton()
        setupFastScroller()
    }
    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        with(binding.webView) {
            loadUrl("https://sites.google.com/view/d4rk7355608/home")
            settings.javaScriptEnabled = true
        }
    }
    private fun setupFloatingButton() {
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, WebViewCodeActivity::class.java))
        }
    }
    private fun setupFastScroller() {
        FastScrollerBuilder(binding.webView).useMd2Style().build()
    }
}