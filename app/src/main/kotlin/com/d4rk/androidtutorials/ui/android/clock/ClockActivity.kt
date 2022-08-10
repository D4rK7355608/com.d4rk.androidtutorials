package com.d4rk.androidtutorials.ui.android.clock
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityClockBinding
class ClockActivity : AppCompatActivity() {
    private lateinit var binding : ActivityClockBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.showClockCodeSyntax.setOnClickListener {
            val intent = Intent(this, ClockCodeActivity::class.java)
            startActivity(intent)
        }
    }
}