package com.d4rk.androidtutorials.ui.android.textboxes.passwordbox
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityPasswordBoxBinding
class PasswordBoxActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPasswordBoxBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindListeners()
    }
    private fun bindListeners() {
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, PasswordBoxCodeActivity::class.java))
        }
        binding.showPasswordButton.setOnClickListener {
            togglePasswordVisibility()
        }
        addKeyListener()
    }
    private fun togglePasswordVisibility() {
        if (binding.showPasswordButton.text == "Show") {
            showPassword()
        } else {
            hidePassword()
        }
    }
    @SuppressLint("SetTextI18n")
    private fun showPassword() {
        binding.editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
        binding.showPasswordButton.setIconResource(R.drawable.ic_visible_off)
        binding.showPasswordButton.text = "Hide"
    }
    @SuppressLint("SetTextI18n")
    private fun hidePassword() {
        binding.editText.transformationMethod = PasswordTransformationMethod.getInstance()
        binding.showPasswordButton.setIconResource(R.drawable.ic_visible)
        binding.showPasswordButton.text = "Show"
    }
    private fun addKeyListener() {
        val edittext = binding.editText
        binding.buttonShowPassword.setOnClickListener {
            Toast.makeText(this, edittext.text, Toast.LENGTH_LONG).show()
        }
    }
}