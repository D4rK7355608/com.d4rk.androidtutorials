package com.d4rk.androidtutorials.ui.android.buttons.image
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityImageButtonsBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class ImageButtonsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityImageButtonsBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageButtonsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        binding.imageButton.setOnClickListener {
            Toast.makeText(this, R.string.toast_image_button, Toast.LENGTH_SHORT).show()
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, ImageButtonsCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
}