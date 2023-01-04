package com.d4rk.androidtutorials.ui.android.clocks.timepicker
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityTimePickerBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
class TimePickerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimePickerBinding
    private val calendar: Calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateTimeInView()
        binding.changeTimeButton.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                updateTimeInView()
            }
            TimePickerDialog(this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, TimePickerCodeActivity::class.java))
        }
    }
    private fun updateTimeInView() {
        val timeFormat = "HH:mm"
        val sdf = SimpleDateFormat(timeFormat, Locale.getDefault())
        binding.timeTextView.text = sdf.format(calendar.time)
    }
}