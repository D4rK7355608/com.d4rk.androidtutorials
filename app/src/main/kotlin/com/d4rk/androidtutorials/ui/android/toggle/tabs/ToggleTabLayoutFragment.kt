package com.d4rk.androidtutorials.ui.android.toggle.tabs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.FragmentToggleLayoutBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
class ToggleTabLayoutFragment : Fragment() {
    private lateinit var _binding: FragmentToggleLayoutBinding
    private val binding get() = _binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentToggleLayoutBinding.inflate(inflater, container, false)
        FastScrollerBuilder(binding.toggleScrollView).useMd2Style().build()
        val inputStream: InputStream = resources.openRawResource(R.raw.text_toggle_xml)
        val byteArrayOutputStream = ByteArrayOutputStream()
        var i: Int
        try {
            i = inputStream.read()
            while (i != - 1) {
                byteArrayOutputStream.write(i)
                i = inputStream.read()
            }
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.toggleXMLText.text = byteArrayOutputStream.toString()
        return binding.root
    }
}

