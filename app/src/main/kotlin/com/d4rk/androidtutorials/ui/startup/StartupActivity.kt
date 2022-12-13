package com.d4rk.androidtutorials.ui.startup
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.MainActivity
import com.d4rk.androidtutorials.databinding.ActivityStartupBinding
class StartupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonBrowsePrivacyPolicyAndTermsOfService.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/d4rk7355608/more/apps/privacy-policy")))
        }
        binding.floatingButtonAgree.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}