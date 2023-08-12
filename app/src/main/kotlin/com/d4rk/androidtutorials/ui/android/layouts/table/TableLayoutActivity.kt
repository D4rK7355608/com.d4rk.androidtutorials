package com.d4rk.androidtutorials.ui.android.layouts.table
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityTableLayoutBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class TableLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTableLayoutBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTableLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, TableLayoutCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
}