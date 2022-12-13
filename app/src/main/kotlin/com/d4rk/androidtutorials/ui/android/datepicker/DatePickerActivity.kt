package com.d4rk.androidtutorials.ui.android.datepicker
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityDatePickerBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
class DatePickerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDatePickerBinding
    private val calendar: Calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateText = simpleDateFormat.format(calendar.time)
        binding.dateTextView.text = dateText
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        binding.changeDateButton.setOnClickListener {
            DatePickerDialog(this@DatePickerActivity, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, DatePickerCodeActivity::class.java))
        }
    }
    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.dateTextView.text = sdf.format(calendar.time)
    }
}