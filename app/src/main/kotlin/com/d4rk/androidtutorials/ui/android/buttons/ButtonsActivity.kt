package com.d4rk.androidtutorials.ui.android.buttons
import android.content.Intent
import android.os.Bundle
import com.d4rk.androidtutorials.databinding.ActivityButtonsBinding
import com.kieronquinn.monetcompat.app.MonetCompatActivity
class ButtonsActivity : MonetCompatActivity() {
    private lateinit var binding : ActivityButtonsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButtonsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.showButtonCodeSyntax.setOnClickListener {
            val intent = Intent(this@ButtonsActivity, ButtonsCodeActivity::class.java)
            startActivity(intent)
        }
    }
}