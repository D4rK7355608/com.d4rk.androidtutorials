package com.d4rk.androidtutorials.ui.feedback
import android.os.Bundle
import android.widget.Toast
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityFeedbackBinding
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.kieronquinn.monetcompat.app.MonetCompatActivity
class FeedbackActivity : MonetCompatActivity() {
    private lateinit var reviewManager: ReviewManager
    private lateinit var binding: ActivityFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init() {
        reviewManager = ReviewManagerFactory.create(this)
        binding.btnRateNow.setOnClickListener {
            showRateDialog()
            Toast.makeText(this@FeedbackActivity, R.string.feedback_toast, Toast.LENGTH_SHORT).show()
        }
    }
    @Suppress("NAME_SHADOWING")
    private fun showRateDialog() {
        val request = reviewManager.requestReviewFlow()
        request.addOnCompleteListener { request ->
            if (request.isSuccessful) {
                val reviewInfo = request.result
                val flow = reviewManager.launchReviewFlow(this, reviewInfo)
                flow.addOnCompleteListener {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                }
            } else {
                // There was some problem, continue regardless of the result.
                // you can show your own rate dialog alert and redirect user to your app page
                // on play store.
            }
        }
    }
}