package com.d4rk.androidtutorials.ui.android.viewbinding
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityViewBindingTutorialBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
class ViewBindingTutorialActivity : AppCompatActivity() {
    private lateinit var binding : ActivityViewBindingTutorialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBindingTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.bindingScrollView).useMd2Style().build()
        val bindingGradle: InputStream = resources.openRawResource(R.raw.text_binding_gradle)
        val bindingActivity: InputStream = resources.openRawResource(R.raw.text_binding_activity)
        val bindingFragment: InputStream = resources.openRawResource(R.raw.text_binding_fragment)
        var i: Int
        val bindingGradleStream = ByteArrayOutputStream()
        try {
            i = bindingGradle.read()
            while (i != - 1) {
                bindingGradleStream.write(i)
                i = bindingGradle.read()
            }
            bindingGradle.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val bindingActivityStream = ByteArrayOutputStream()
        try {
            i = bindingActivity.read()
            while (i != - 1) {
                bindingActivityStream.write(i)
                i = bindingActivity.read()
            }
            bindingActivity.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val bindingFragmentStream = ByteArrayOutputStream()
        try {
            i = bindingFragment.read()
            while (i != - 1) {
                bindingFragmentStream.write(i)
                i = bindingFragment.read()
            }
            bindingFragment.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.moreAboutViewBindingButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/topic/libraries/view-binding"))
            startActivity(intent)
        }
        binding.bindingText.text = bindingGradleStream.toString()
        binding.bindingActivitiesText.text = bindingActivityStream.toString()
        binding.bindingFragmentsText.text = bindingFragmentStream.toString()
    }
}