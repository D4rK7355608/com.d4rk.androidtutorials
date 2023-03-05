package com.d4rk.androidtutorials.ui.android.clocks.clock
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityClockBinding
class ClockActivity : AppCompatActivity() {
    private lateinit var binding : ActivityClockBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, ClockCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
}