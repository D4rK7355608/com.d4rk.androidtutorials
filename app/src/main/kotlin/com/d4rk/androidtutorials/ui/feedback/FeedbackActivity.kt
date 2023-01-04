package com.d4rk.androidtutorials.ui.feedback
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityFeedbackBinding
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class FeedbackActivity : AppCompatActivity() {
    private lateinit var reviewManager: ReviewManager
    private lateinit var binding: ActivityFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        init()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_feedback, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dev_mail -> {
                sendFeedbackEmail()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun sendFeedbackEmail() {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "text/email"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("d4rk7355608@gmail.com"))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for Android Studio Tutorials")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear developer, ")
        startActivity(Intent.createChooser(emailIntent, "Send mail to Developer:"))
    }
    private fun init() {
        reviewManager = ReviewManagerFactory.create(this)
        binding.buttonRateNow.setOnClickListener {
            showRateDialog()
            Toast.makeText(this, R.string.toast_feedback, Toast.LENGTH_SHORT).show()
        }
    }
    @Suppress("ControlFlowWithEmptyBody")
    private fun showRateDialog() {
        reviewManager.requestReviewFlow()
            .addOnCompleteListener {
                it.result?.let { reviewInfo ->
                    reviewManager.launchReviewFlow(this, reviewInfo).also {
                        // Add any additional code here, if needed
                    }
                }
            }
    }
}