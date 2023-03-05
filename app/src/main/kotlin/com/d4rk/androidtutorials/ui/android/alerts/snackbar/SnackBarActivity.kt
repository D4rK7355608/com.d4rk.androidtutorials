package com.d4rk.androidtutorials.ui.android.alerts.snackbar
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivitySnackBarBinding
import com.google.android.material.snackbar.Snackbar
class SnackBarActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySnackBarBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySnackBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val snackbar = Snackbar.make(binding.root, R.string.this_is_a_snackbar, Snackbar.LENGTH_INDEFINITE)
            snackbar.setAction(android.R.string.ok) {
                snackbar.dismiss()
            }
            snackbar.show()
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, SnackBarCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
}