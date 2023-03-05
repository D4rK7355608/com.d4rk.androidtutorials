package com.d4rk.androidtutorials.ui.android.images
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityImagesBinding
class ImagesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityImagesBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, ImagesCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
}