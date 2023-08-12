package com.d4rk.androidtutorials.ui.android.layouts.relative
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityRelativeLayoutBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class RelativeLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRelativeLayoutBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelativeLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, RelativeLayoutCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
}