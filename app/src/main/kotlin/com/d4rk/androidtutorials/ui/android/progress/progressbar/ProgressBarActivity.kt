package com.d4rk.androidtutorials.ui.android.progress.progressbar
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityProgressBarBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class ProgressBarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProgressBarBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        binding.progressBar.hide()
        binding.buttonDownloadHorizontal.setOnClickListener {
            var progressStatus = 0
            Thread {
                while (progressStatus < 100) {
                    progressStatus += 1
                    try {
                        Thread.sleep(50)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    handler.post {
                        binding.progressBarHorizontal.progress = progressStatus
                    }
                }
            }.start()
        }
        binding.buttonDownload.setOnClickListener {
            binding.progressBar.show()
            handler.postDelayed({
                binding.progressBar.hide()
            }, 5000)
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, ProgressBarCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
}