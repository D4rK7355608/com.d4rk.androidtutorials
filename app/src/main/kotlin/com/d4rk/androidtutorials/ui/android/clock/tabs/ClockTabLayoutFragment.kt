package com.d4rk.androidtutorials.ui.android.clock.tabs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.FragmentClockLayoutBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
class ClockTabLayoutFragment : Fragment() {
    private lateinit var _binding: FragmentClockLayoutBinding
    private val binding get() = _binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentClockLayoutBinding.inflate(inflater, container, false)
        FastScrollerBuilder(binding.clockScrollView).useMd2Style().build()
        val digitalClockXMLTextInput: InputStream = resources.openRawResource(R.raw.text_clock_digital_xml)
        val digitalClockXMLText = ByteArrayOutputStream()
        var i: Int
        try {
            i = digitalClockXMLTextInput.read()
            while (i != - 1) {
                digitalClockXMLText.write(i)
                i = digitalClockXMLTextInput.read()
            }
            digitalClockXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.digitalClockXMLText.text = digitalClockXMLText.toString()
        val textClockXMLTextInput: InputStream = resources.openRawResource(R.raw.text_clock_xml)
        val textClockXMLText = ByteArrayOutputStream()
        try {
            i = textClockXMLTextInput.read()
            while (i != - 1) {
                textClockXMLText.write(i)
                i = textClockXMLTextInput.read()
            }
            textClockXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textClockXMLText.text = textClockXMLText.toString()
        val analogClockXMLTextInput: InputStream = resources.openRawResource(R.raw.text_clock_analog_xml)
        val analogClockXMLText = ByteArrayOutputStream()
        try {
            i = analogClockXMLTextInput.read()
            while (i != - 1) {
                analogClockXMLText.write(i)
                i = analogClockXMLTextInput.read()
            }
            analogClockXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.analogClockXMLText.text = analogClockXMLText.toString()
        return binding.root
    }
}