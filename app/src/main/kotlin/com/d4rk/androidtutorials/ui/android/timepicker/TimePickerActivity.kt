package com.d4rk.androidtutorials.ui.android.timepicker
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityTimePickerBinding
import java.text.SimpleDateFormat
import java.util.Calendar
class TimePickerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTimePickerBinding
    private val calendar: Calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val dateTime = simpleDateFormat.format(calendar.time)
        binding.timeTextView.text = dateTime
        binding.changeTimeButton.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                binding.timeTextView.text = SimpleDateFormat("HH:mm").format(calendar.time)
            }
            TimePickerDialog(this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }
        binding.showButtonCodeSyntax.setOnClickListener {
            val intent = Intent(this, TimePickerCodeActivity::class.java)
            startActivity(intent)
        }
    }
}