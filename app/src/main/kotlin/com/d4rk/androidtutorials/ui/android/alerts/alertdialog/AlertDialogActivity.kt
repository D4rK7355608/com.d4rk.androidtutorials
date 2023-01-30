package com.d4rk.androidtutorials.ui.android.alerts.alertdialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityAlertDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
class AlertDialogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlertDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlertDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val alertDialog = createAlertDialog()
        binding.button.setOnClickListener {
            alertDialog.show()
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, AlertDialogCodeActivity::class.java))
        }
    }
    private fun createAlertDialog(): MaterialAlertDialogBuilder {
        return MaterialAlertDialogBuilder(this).apply {
            setTitle(R.string.title_alert_dialog)
            setMessage(R.string.alert_dialog_message)
            setIcon(R.drawable.ic_shop)
            setPositiveButton(android.R.string.ok, null)
            setNegativeButton(android.R.string.cancel, null)
        }
    }
}