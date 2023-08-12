package com.d4rk.androidtutorials.ui.android.clocks.chronometer
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityChronometerBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class ChronometerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChronometerBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChronometerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, ChronometerCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
        binding.buttonStart.setOnClickListener {
            binding.chronometer.start()
        }
        binding.buttonStop.setOnClickListener {
            binding.chronometer.stop()
        }
        binding.buttonReset.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime()
        }
    }
}