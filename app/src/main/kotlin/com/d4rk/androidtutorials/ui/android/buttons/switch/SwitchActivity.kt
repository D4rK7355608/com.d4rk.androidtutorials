package com.d4rk.androidtutorials.ui.android.buttons.switch
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivitySwitchBinding
import com.google.android.material.snackbar.Snackbar
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class SwitchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySwitchBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySwitchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        setOnClickListeners()
    }
    private fun setOnClickListeners() {
        binding.materialSwitchPreference.setOnClickListener {
            Snackbar.make(binding.root, R.string.material_switch_preference, Snackbar.LENGTH_SHORT).show()
        }
        binding.materialSwitch.setOnClickListener {
            Snackbar.make(binding.root, R.string.material_switch, Snackbar.LENGTH_SHORT).show()
        }
        binding.switchMaterial.setOnClickListener {
            Snackbar.make(binding.root, R.string.switch_material, Snackbar.LENGTH_SHORT).show()
        }
        binding.buttonToggle.setOnClickListener {
            Snackbar.make(binding.root, R.string.toggle_button, Snackbar.LENGTH_SHORT).show()
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, SwitchCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
}