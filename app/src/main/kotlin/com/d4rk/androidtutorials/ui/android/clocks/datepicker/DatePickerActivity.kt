package com.d4rk.androidtutorials.ui.android.clocks.datepicker
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityDatePickerBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
class DatePickerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDatePickerBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    private val calendar: Calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateDateInView()
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }
        binding.changeDateButton.setOnClickListener {
            DatePickerDialog(this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, DatePickerCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
    private fun updateDateInView() {
        binding.dateTextView.text = dateFormat.format(calendar.time)
    }
}