package com.d4rk.androidtutorials.ui.android.toggle
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityToggleBinding
import com.kieronquinn.monetcompat.app.MonetCompatActivity
class ToggleActivity : MonetCompatActivity() {
    private lateinit var binding : ActivityToggleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToggleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.monetSwitch.setOnClickListener {
            Toast.makeText(this@ToggleActivity,  R.string.monetSwitchToast, Toast.LENGTH_SHORT).show()
        }
        binding.appCompatToggleButton.setOnClickListener {
            Toast.makeText(this@ToggleActivity, R.string.appCompatToggleButtonToast, Toast.LENGTH_SHORT).show()
        }
        binding.switchMaterial.setOnClickListener {
            Toast.makeText(this@ToggleActivity, R.string.switchMaterialToast, Toast.LENGTH_SHORT).show()
        }
        binding.showToggleCodeSyntax.setOnClickListener {
            val intent = Intent(this@ToggleActivity, ToggleCodeActivity::class.java)
            startActivity(intent)
        }
    }
}