package com.d4rk.androidtutorials.ui.android.buttons
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityButtonsBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class ButtonsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityButtonsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButtonsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        binding.buttonNormal.setOnClickListener {
            Toast.makeText(this, R.string.toast_button_normal, Toast.LENGTH_SHORT).show()
        }
        binding.buttonOutlined.setOnClickListener {
            Toast.makeText(this, R.string.toast_button_outlined, Toast.LENGTH_SHORT).show()
        }
        binding.buttonElevated.setOnClickListener {
            Toast.makeText(this, R.string.toast_button_elevated, Toast.LENGTH_SHORT).show()
        }
        binding.buttonNormalIcon.setOnClickListener {
            Toast.makeText(this, R.string.toast_button_normal_icon, Toast.LENGTH_SHORT).show()
        }
        binding.buttonOutlinedIcon.setOnClickListener {
            Toast.makeText(this, R.string.toast_button_outlined_icon, Toast.LENGTH_SHORT).show()
        }
        binding.buttonElevatedIcon.setOnClickListener {
            Toast.makeText(this, R.string.toast_button_elevated_icon, Toast.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonPrimary.setOnClickListener {
            Toast.makeText(this, R.string.toast_extended_floating_button_primary, Toast.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonSecondary.setOnClickListener {
            Toast.makeText(this, R.string.toast_extended_floating_button_secondary, Toast.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonSurface.setOnClickListener {
            Toast.makeText(this, R.string.toast_extended_floating_button_surface, Toast.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonTertiary.setOnClickListener {
            Toast.makeText(this, R.string.toast_extended_floating_button_tertiary, Toast.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonPrimaryIcon.setOnClickListener {
            Toast.makeText(this, R.string.toast_extended_floating_button_primary_icon, Toast.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonSecondaryIcon.setOnClickListener {
            Toast.makeText(this, R.string.toast_extended_floating_button_secondary_icon, Toast.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonSurfaceIcon.setOnClickListener {
            Toast.makeText(this, R.string.toast_extended_floating_button_surface_icon, Toast.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonTertiaryIcon.setOnClickListener {
            Toast.makeText(this, R.string.toast_extended_floating_button_tertiary_icon, Toast.LENGTH_SHORT).show()
        }
        binding.floatingButtonPrimary.setOnClickListener {
            Toast.makeText(this, R.string.toast_floating_button_primary, Toast.LENGTH_SHORT).show()
        }
        binding.floatingButtonSecondary.setOnClickListener {
            Toast.makeText(this, R.string.toast_floating_button_secondary, Toast.LENGTH_SHORT).show()
        }
        binding.floatingButtonSurface.setOnClickListener {
            Toast.makeText(this, R.string.toast_floating_button_surface, Toast.LENGTH_SHORT).show()
        }
        binding.floatingButtonTertiary.setOnClickListener {
            Toast.makeText(this, R.string.toast_floating_button_tertiary, Toast.LENGTH_SHORT).show()
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, ButtonsCodeActivity::class.java))
        }
    }
}