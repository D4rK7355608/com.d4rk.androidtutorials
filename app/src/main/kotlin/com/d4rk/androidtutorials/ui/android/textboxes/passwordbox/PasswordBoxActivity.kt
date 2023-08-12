package com.d4rk.androidtutorials.ui.android.textboxes.passwordbox
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityPasswordBoxBinding
import com.google.android.material.snackbar.Snackbar
class PasswordBoxActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPasswordBoxBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindListeners()
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
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
            Snackbar.make(binding.root, edittext.text.toString(), Snackbar.LENGTH_LONG).show()
        }
    }
}