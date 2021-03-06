package com.d4rk.androidtutorials.ui.android
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityAndroidStartProjectBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class AndroidStartProjectActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAndroidStartProjectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAndroidStartProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.startProjectScroll).useMd2Style().build()
        binding.step3Sum.movementMethod = LinkMovementMethod.getInstance()
    }
}