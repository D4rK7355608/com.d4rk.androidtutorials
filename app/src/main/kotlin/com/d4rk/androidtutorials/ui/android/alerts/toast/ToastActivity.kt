package com.d4rk.androidtutorials.ui.android.alerts.toast
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityToastBinding
class ToastActivity : AppCompatActivity() {
    private lateinit var binding: ActivityToastBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            Toast.makeText(this, R.string.toast_this_is_a_toast, Toast.LENGTH_SHORT).show()
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, ToastCodeActivity::class.java))
        }
    }
}