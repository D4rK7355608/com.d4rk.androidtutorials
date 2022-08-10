package com.d4rk.androidtutorials.ui.android.buttons.tabs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.FragmentButtonsLayoutBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
class ButtonsTabLayoutFragment : Fragment() {
    private lateinit var _binding: FragmentButtonsLayoutBinding
    private val binding get() = _binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentButtonsLayoutBinding.inflate(inflater, container, false)
        FastScrollerBuilder(binding.buttonsScrollView).useMd2Style().build()
        val inputStream: InputStream = resources.openRawResource(R.raw.text_buttons_xml)
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
        binding.buttonsXMLText.text = byteArrayOutputStream.toString()
        return binding.root
    }
}