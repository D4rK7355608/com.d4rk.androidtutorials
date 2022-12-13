package com.d4rk.androidtutorials.ui.android.alertdialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityAlertDialogBinding
class AlertDialogActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAlertDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlertDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
            alertDialog.setTitle(R.string.title_alert_dialog)
            alertDialog.setMessage(R.string.alert_dialog_message)
            alertDialog.create()
            alertDialog.setPositiveButton(android.R.string.ok, null)
            alertDialog.setNegativeButton(android.R.string.cancel, null)
            alertDialog.show()
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, AlertDialogCodeActivity::class.java))
        }
    }
}