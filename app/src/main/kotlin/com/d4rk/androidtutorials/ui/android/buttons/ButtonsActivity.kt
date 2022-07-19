package com.d4rk.androidtutorials.ui.android.buttons
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityButtonsBinding
class ButtonsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityButtonsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButtonsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button1.setOnClickListener {
            Toast.makeText(this, R.string.buttonToast, Toast.LENGTH_SHORT).show()
        }
        binding.button2.setOnClickListener {
            Toast.makeText(this, R.string.button2Toast, Toast.LENGTH_SHORT).show()
        }
        binding.button3.setOnClickListener {
            Toast.makeText(this, R.string.button3Toast, Toast.LENGTH_SHORT).show()
        }
        binding.showButtonCodeSyntax.setOnClickListener {
            val intent = Intent(this, ButtonsCodeActivity::class.java)
            startActivity(intent)
        }
    }
}