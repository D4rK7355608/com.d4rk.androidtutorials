package com.d4rk.androidtutorials.ui.android.buttons.tabs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.FragmentButtonsLayoutBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
class ButtonsTabLayoutFragment : Fragment() {
    private lateinit var _binding: FragmentButtonsLayoutBinding
    private val binding get() = _binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentButtonsLayoutBinding.inflate(inflater, container, false)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        MobileAds.initialize(requireContext())
        val adRequestBuilder = AdRequest.Builder().build()
        binding.adView.loadAd(adRequestBuilder)
        var i: Int
        val normalButtonXMLTextInput: InputStream = resources.openRawResource(R.raw.text_button_normal_xml)
        val normalButtonXMLText = ByteArrayOutputStream()
        try {
            i = normalButtonXMLTextInput.read()
            while (i != - 1) {
                normalButtonXMLText.write(i)
                i = normalButtonXMLTextInput.read()
            }
            normalButtonXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewButtonNormalXml.text = normalButtonXMLText.toString()
        val outlinedButtonXMLTextInput: InputStream = resources.openRawResource(R.raw.text_button_outlined_xml)
        val outlinedButtonXMLText = ByteArrayOutputStream()
        try {
            i = outlinedButtonXMLTextInput.read()
            while (i != - 1) {
                outlinedButtonXMLText.write(i)
                i = outlinedButtonXMLTextInput.read()
            }
            outlinedButtonXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewButtonOutlinedXml.text = outlinedButtonXMLText.toString()
        val elevatedButtonXMLTextInput: InputStream = resources.openRawResource(R.raw.text_button_elevated_xml)
        val elevatedButtonXMLText = ByteArrayOutputStream()
        try {
            i = elevatedButtonXMLTextInput.read()
            while (i != - 1) {
                elevatedButtonXMLText.write(i)
                i = elevatedButtonXMLTextInput.read()
            }
            elevatedButtonXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewButtonElevatedXml.text = elevatedButtonXMLText.toString()
        val buttonNormalIconXMLTextInput: InputStream = resources.openRawResource(R.raw.text_button_normal_icon_xml)
        val buttonNormalIconXMLText = ByteArrayOutputStream()
        try {
            i = buttonNormalIconXMLTextInput.read()
            while (i != - 1) {
                buttonNormalIconXMLText.write(i)
                i = buttonNormalIconXMLTextInput.read()
            }
            buttonNormalIconXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewButtonNormalIconXml.text = buttonNormalIconXMLText.toString()
        val buttonOutlinedIconXMLTextInput: InputStream = resources.openRawResource(R.raw.text_button_outlined_icon_xml)
        val buttonOutlinedIconXMLText = ByteArrayOutputStream()
        try {
            i = buttonOutlinedIconXMLTextInput.read()
            while (i != - 1) {
                buttonOutlinedIconXMLText.write(i)
                i = buttonOutlinedIconXMLTextInput.read()
            }
            buttonOutlinedIconXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewButtonOutlinedIconXml.text = buttonOutlinedIconXMLText.toString()
        val buttonElevatedIconXMLTextInput: InputStream = resources.openRawResource(R.raw.text_button_elevated_icon_xml)
        val buttonElevatedIconXMLText = ByteArrayOutputStream()
        try {
            i = buttonElevatedIconXMLTextInput.read()
            while (i != - 1) {
                buttonElevatedIconXMLText.write(i)
                i = buttonElevatedIconXMLTextInput.read()
            }
            buttonElevatedIconXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewButtonElevatedIconXml.text = buttonElevatedIconXMLText.toString()
        val extendedFloatingButtonPrimaryXMLTextInput: InputStream = resources.openRawResource(R.raw.text_extended_floating_button_primary_xml)
        val extendedFloatingButtonPrimaryXMLText = ByteArrayOutputStream()
        try {
            i = extendedFloatingButtonPrimaryXMLTextInput.read()
            while (i != - 1) {
                extendedFloatingButtonPrimaryXMLText.write(i)
                i = extendedFloatingButtonPrimaryXMLTextInput.read()
            }
            extendedFloatingButtonPrimaryXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewExtendedFloatingButtonPrimaryXml.text = extendedFloatingButtonPrimaryXMLText.toString()
        val extendedFloatingButtonSecondaryXMLTextInput: InputStream = resources.openRawResource(R.raw.text_extended_floating_button_secondary_xml)
        val extendedFloatingButtonSecondaryXMLText = ByteArrayOutputStream()
        try {
            i = extendedFloatingButtonSecondaryXMLTextInput.read()
            while (i != - 1) {
                extendedFloatingButtonSecondaryXMLText.write(i)
                i = extendedFloatingButtonSecondaryXMLTextInput.read()
            }
            extendedFloatingButtonSecondaryXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewExtendedFloatingButtonSecondaryXml.text = extendedFloatingButtonSecondaryXMLText.toString()
        val extendedFloatingButtonSurfaceXMLTextInput: InputStream = resources.openRawResource(R.raw.text_extended_floating_button_surface_xml)
        val extendedFloatingButtonSurfaceXMLText = ByteArrayOutputStream()
        try {
            i = extendedFloatingButtonSurfaceXMLTextInput.read()
            while (i != - 1) {
                extendedFloatingButtonSurfaceXMLText.write(i)
                i = extendedFloatingButtonSurfaceXMLTextInput.read()
            }
            extendedFloatingButtonSurfaceXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewExtendedFloatingButtonSurfaceXml.text = extendedFloatingButtonSurfaceXMLText.toString()
        val extendedFloatingButtonTertiaryXMLTextInput: InputStream = resources.openRawResource(R.raw.text_extended_floating_button_tertiary_xml)
        val extendedFloatingButtonTertiaryXMLText = ByteArrayOutputStream()
        try {
            i = extendedFloatingButtonTertiaryXMLTextInput.read()
            while (i != - 1) {
                extendedFloatingButtonTertiaryXMLText.write(i)
                i = extendedFloatingButtonTertiaryXMLTextInput.read()
            }
            extendedFloatingButtonTertiaryXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewExtendedFloatingButtonTertiaryXml.text = extendedFloatingButtonTertiaryXMLText.toString()
        val extendedFloatingButtonPrimaryIconXMLTextInput: InputStream = resources.openRawResource(R.raw.text_extended_floating_button_primary_icon_xml)
        val extendedFloatingButtonPrimaryIconXMLText = ByteArrayOutputStream()
        try {
            i = extendedFloatingButtonPrimaryIconXMLTextInput.read()
            while (i != - 1) {
                extendedFloatingButtonPrimaryIconXMLText.write(i)
                i = extendedFloatingButtonPrimaryIconXMLTextInput.read()
            }
            extendedFloatingButtonPrimaryIconXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewExtendedFloatingButtonPrimaryIconXml.text = extendedFloatingButtonPrimaryIconXMLText.toString()
        val extendedFloatingButtonSecondaryIconXMLTextInput: InputStream = resources.openRawResource(R.raw.text_extended_floating_button_secondary_icon_xml)
        val extendedFloatingButtonSecondaryIconXMLText = ByteArrayOutputStream()
        try {
            i = extendedFloatingButtonSecondaryIconXMLTextInput.read()
            while (i != - 1) {
                extendedFloatingButtonSecondaryIconXMLText.write(i)
                i = extendedFloatingButtonSecondaryIconXMLTextInput.read()
            }
            extendedFloatingButtonSecondaryIconXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewExtendedFloatingButtonSecondaryIconXml.text = extendedFloatingButtonSecondaryIconXMLText.toString()
        val extendedFloatingButtonSurfaceIconXMLTextInput: InputStream = resources.openRawResource(R.raw.text_extended_floating_button_surface_icon_xml)
        val extendedFloatingButtonSurfaceIconXMLText = ByteArrayOutputStream()
        try {
            i = extendedFloatingButtonSurfaceIconXMLTextInput.read()
            while (i != - 1) {
                extendedFloatingButtonSurfaceIconXMLText.write(i)
                i = extendedFloatingButtonSurfaceIconXMLTextInput.read()
            }
            extendedFloatingButtonSurfaceIconXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewExtendedFloatingButtonSurfaceIconXml.text = extendedFloatingButtonSurfaceIconXMLText.toString()
        val extendedFloatingButtonTertiaryIconXMLTextInput: InputStream = resources.openRawResource(R.raw.text_extended_floating_button_tertiary_icon_xml)
        val extendedFloatingButtonTertiaryIconXMLText = ByteArrayOutputStream()
        try {
            i = extendedFloatingButtonTertiaryIconXMLTextInput.read()
            while (i != - 1) {
                extendedFloatingButtonTertiaryIconXMLText.write(i)
                i = extendedFloatingButtonTertiaryIconXMLTextInput.read()
            }
            extendedFloatingButtonTertiaryIconXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val floatingButtonPrimaryXMLTextInput: InputStream = resources.openRawResource(R.raw.text_floating_button_primary_xml)
        val floatingButtonPrimaryXMLText = ByteArrayOutputStream()
        try {
            i = floatingButtonPrimaryXMLTextInput.read()
            while (i != - 1) {
                floatingButtonPrimaryXMLText.write(i)
                i = floatingButtonPrimaryXMLTextInput.read()
            }
            floatingButtonPrimaryXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewFloatingButtonPrimaryXml.text = floatingButtonPrimaryXMLText.toString()
        val floatingButtonSecondaryXMLTextInput: InputStream = resources.openRawResource(R.raw.text_floating_button_secondary_xml)
        val floatingButtonSecondaryXMLText = ByteArrayOutputStream()
        try {
            i = floatingButtonSecondaryXMLTextInput.read()
            while (i != - 1) {
                floatingButtonSecondaryXMLText.write(i)
                i = floatingButtonSecondaryXMLTextInput.read()
            }
            floatingButtonSecondaryXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewFloatingButtonSecondaryXml.text = floatingButtonSecondaryXMLText.toString()
        val floatingButtonSurfaceXMLTextInput: InputStream = resources.openRawResource(R.raw.text_floating_button_surface_xml)
        val floatingButtonSurfaceXMLText = ByteArrayOutputStream()
        try {
            i = floatingButtonSurfaceXMLTextInput.read()
            while (i != - 1) {
                floatingButtonSurfaceXMLText.write(i)
                i = floatingButtonSurfaceXMLTextInput.read()
            }
            floatingButtonSurfaceXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewFloatingButtonSurfaceXml.text = floatingButtonSurfaceXMLText.toString()
        val floatingButtonTertiaryXMLTextInput: InputStream = resources.openRawResource(R.raw.text_floating_button_tertiary_xml)
        val floatingButtonTertiaryXMLText = ByteArrayOutputStream()
        try {
            i = floatingButtonTertiaryXMLTextInput.read()
            while (i != - 1) {
                floatingButtonTertiaryXMLText.write(i)
                i = floatingButtonTertiaryXMLTextInput.read()
            }
            floatingButtonTertiaryXMLTextInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewFloatingButtonTertiaryXml.text = floatingButtonTertiaryXMLText.toString()
        binding.textViewExtendedFloatingButtonTertiaryIconXml.text = extendedFloatingButtonTertiaryIconXMLText.toString()
        return binding.root
    }
}