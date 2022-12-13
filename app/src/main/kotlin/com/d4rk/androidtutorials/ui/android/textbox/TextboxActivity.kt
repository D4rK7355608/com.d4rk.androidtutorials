package com.d4rk.androidtutorials.ui.android.textbox
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityTextboxBinding
class TextboxActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTextboxBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextboxBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, TextboxCodeActivity::class.java))
        }
        addKeyListener()
    }
    private fun addKeyListener() {
        val edittext = findViewById<View>(R.id.editText) as EditText
        binding.buttonPrintEdit.setOnClickListener {
            Toast.makeText(this, edittext.text, Toast.LENGTH_LONG).show()
        }
    }
}