package com.d4rk.androidtutorials.ui.android.buttons.buttons
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityButtonsBinding
import com.google.android.material.snackbar.Snackbar
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class ButtonsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityButtonsBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButtonsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        binding.buttonNormal.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.button_normal) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.buttonOutlined.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.button_outlined) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.buttonElevated.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.button_elevated) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.buttonNormalIcon.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.button_normal_icon) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.buttonOutlinedIcon.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.button_outlined_icon) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.buttonElevatedIcon.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.button_elevated_icon) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonPrimary.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.extended_floating_button_primary) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonSecondary.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.extended_floating_button_secondary) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonSurface.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.extended_floating_button_surface) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonTertiary.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.extended_floating_button_tertiary) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonPrimaryIcon.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.extended_floating_button_primary_icon) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonSecondaryIcon.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.extended_floating_button_secondary_icon) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonSurfaceIcon.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.extended_floating_button_surface_icon) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.extendedFloatingButtonTertiaryIcon.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.extended_floating_button_tertiary_icon) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.floatingButtonPrimary.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.floating_button_primary_icon) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.floatingButtonSecondary.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.floating_button_secondary_icon) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.floatingButtonSurface.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.floating_button_surface_icon) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.floatingButtonTertiary.setOnClickListener {
            Snackbar.make(binding.root, getString(R.string.floating_button_tertiary_icon) + " " + getString(R.string.snack_bar_clicked), Snackbar.LENGTH_SHORT).show()
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, ButtonsCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
}