package com.d4rk.androidtutorials.ui.android.views.grid
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityGridViewBinding
class GirdViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGridViewBinding
    private val numbers = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
        binding.gridView.adapter = adapter
        binding.gridView.onItemClickListener = AdapterView.OnItemClickListener { _, view, _, _ -> Toast.makeText(applicationContext, (view as TextView).text, Toast.LENGTH_SHORT).show() }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, GirdViewCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
}