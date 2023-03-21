package com.d4rk.androidtutorials.ui.android.buttons.toggle
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityToggleBinding
import com.kieronquinn.monetcompat.app.MonetCompatActivity
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class ToggleActivity : MonetCompatActivity() {
    private lateinit var binding: ActivityToggleBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToggleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        setOnClickListeners()
    }
    private fun setOnClickListeners() {
        binding.switchMonet.setOnClickListener {
            Toast.makeText(this,  R.string.toast_monet_switch, Toast.LENGTH_SHORT).show()
        }
        binding.buttonToggle.setOnClickListener {
            Toast.makeText(this, R.string.toast_compat_toggle_button, Toast.LENGTH_SHORT).show()
        }
        binding.switchMaterial.setOnClickListener {
            Toast.makeText(this, R.string.toast_switch_material, Toast.LENGTH_SHORT).show()
        }
        binding.materialSwitch.setOnClickListener {
            Toast.makeText(this, R.string.toast_material_switch, Toast.LENGTH_SHORT).show()
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, ToggleCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
}