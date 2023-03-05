package com.d4rk.androidtutorials.ui.android.reviews.ratingbar
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityRatingBarBinding
class RatingBarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRatingBarBinding
    private var rating: Float = 0f
    private lateinit var formattedString: String
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRatingBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        formattedString = String.format(getString(R.string.stars), rating)
        binding.textViewRatingValue.text = formattedString
        binding.ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, rating, _ ->
            this.rating = rating
            updateRatingText()
        }
        binding.button.setOnClickListener {
            showRatingToast()
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, RatingBarCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
    private fun updateRatingText() {
        formattedString = String.format(getString(R.string.stars), rating)
        binding.textViewRatingValue.text = formattedString
    }
    private fun showRatingToast() {
        formattedString = String.format(getString(R.string.rating), rating)
        Toast.makeText(this, formattedString, Toast.LENGTH_SHORT).show()
    }
}
