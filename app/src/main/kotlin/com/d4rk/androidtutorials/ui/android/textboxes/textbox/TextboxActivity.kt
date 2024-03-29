package com.d4rk.androidtutorials.ui.android.textboxes.textbox
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityTextboxBinding
import com.google.android.material.snackbar.Snackbar
class TextboxActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextboxBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextboxBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindListeners()
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
    private fun bindListeners() {
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, TextboxCodeActivity::class.java))
        }
        addKeyListener()
    }
    private fun addKeyListener() {
        val edittext = binding.editText
        binding.buttonPrintEdit.setOnClickListener {
            Snackbar.make(binding.root, edittext.text.toString(), Snackbar.LENGTH_LONG).show()
        }
    }
}