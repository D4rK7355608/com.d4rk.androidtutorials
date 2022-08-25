package com.d4rk.androidtutorials.ui.android.passwordbox
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityPasswordBoxBinding
class PasswordBoxActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPasswordBoxBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.showButtonCodeSyntax.setOnClickListener {
            val intent = Intent(this, PasswordBoxCodeActivity::class.java)
            startActivity(intent)
        }
        binding.showPasswordButton.setIconResource(R.drawable.ic_visible)
        binding.showPasswordButton.setOnClickListener {
            if(binding.showPasswordButton.text.toString() == "Show"){
                binding.editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.showPasswordButton.setIconResource(R.drawable.ic_visible_off)
                binding.showPasswordButton.text = "Hide"
            } else{
                binding.editText.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.showPasswordButton.setIconResource(R.drawable.ic_visible)
                binding.showPasswordButton.text = "Show"
            }
        }
        addKeyListener()
    }
    private fun addKeyListener() {
        val edittext = findViewById<View>(R.id.editText) as EditText
        binding.buttonShowPassword.setOnClickListener {
            Toast.makeText(this, edittext.text, Toast.LENGTH_LONG).show()
        }
    }
}