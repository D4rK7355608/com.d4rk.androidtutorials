package com.d4rk.androidtutorials.ui.android.buttons.radio
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityRadioButtonsBinding
class RadioButtonsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRadioButtonsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRadioButtonsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, RadioButtonsCodeActivity::class.java))
        }
        binding.buttonDisplay.setOnClickListener {
            val selectedId = binding.radioGroup.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(selectedId)
            Toast.makeText(this, radioButton.text, Toast.LENGTH_SHORT).show()
        }
    }
}