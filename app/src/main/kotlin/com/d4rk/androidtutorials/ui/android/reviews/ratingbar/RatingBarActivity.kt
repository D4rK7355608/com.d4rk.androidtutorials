package com.d4rk.androidtutorials.ui.android.reviews.ratingbar
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityRatingBarBinding
import com.google.android.material.snackbar.Snackbar
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
            showRatingSnackbar()
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
    private fun showRatingSnackbar() {
        formattedString = String.format(getString(R.string.snack_rating), rating)
        Snackbar.make(binding.root, formattedString, Snackbar.LENGTH_SHORT).show()
    }
}
