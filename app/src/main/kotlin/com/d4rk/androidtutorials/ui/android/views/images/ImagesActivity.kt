package com.d4rk.androidtutorials.ui.android.views.images
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityImagesBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class ImagesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityImagesBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, ImagesCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
}