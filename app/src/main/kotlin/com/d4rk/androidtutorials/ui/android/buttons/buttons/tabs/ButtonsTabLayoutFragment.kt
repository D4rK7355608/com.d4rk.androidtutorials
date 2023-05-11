package com.d4rk.androidtutorials.ui.android.buttons.buttons.tabs
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.FragmentButtonsLayoutBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import java.io.InputStream
class ButtonsTabLayoutFragment : Fragment() {
    private lateinit var binding: FragmentButtonsLayoutBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentButtonsLayoutBinding.inflate(inflater, container, false)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        MobileAds.initialize(requireContext())
        binding.adView.loadAd(AdRequest.Builder().build())
        val buttonXMLResources = listOf(
            R.raw.text_button_normal_xml to binding.textViewButtonNormalXml,
            R.raw.text_button_outlined_xml to binding.textViewButtonOutlinedXml,
            R.raw.text_button_elevated_xml to binding.textViewButtonElevatedXml,
            R.raw.text_button_normal_icon_xml to binding.textViewButtonNormalIconXml,
            R.raw.text_button_outlined_icon_xml to binding.textViewButtonOutlinedIconXml,
            R.raw.text_button_elevated_icon_xml to binding.textViewButtonElevatedIconXml,
            R.raw.text_extended_floating_button_primary_xml to binding.textViewExtendedFloatingButtonPrimaryXml,
            R.raw.text_extended_floating_button_secondary_xml to binding.textViewExtendedFloatingButtonSecondaryXml,
            R.raw.text_extended_floating_button_surface_xml to binding.textViewExtendedFloatingButtonSurfaceXml,
            R.raw.text_extended_floating_button_tertiary_xml to binding.textViewExtendedFloatingButtonTertiaryXml,
            R.raw.text_extended_floating_button_primary_icon_xml to binding.textViewExtendedFloatingButtonPrimaryIconXml,
            R.raw.text_extended_floating_button_secondary_icon_xml to binding.textViewExtendedFloatingButtonSecondaryIconXml,
            R.raw.text_extended_floating_button_surface_icon_xml to binding.textViewExtendedFloatingButtonSurfaceIconXml,
            R.raw.text_extended_floating_button_tertiary_icon_xml to binding.textViewExtendedFloatingButtonTertiaryIconXml,
            R.raw.text_floating_button_primary_xml to binding.textViewFloatingButtonPrimaryXml,
            R.raw.text_floating_button_secondary_xml to binding.textViewFloatingButtonSecondaryXml,
            R.raw.text_floating_button_surface_xml to binding.textViewFloatingButtonSurfaceXml,
            R.raw.text_floating_button_tertiary_xml to binding.textViewFloatingButtonTertiaryXml,
        )
        for ((resourceId, textView) in buttonXMLResources) {
            val inputStream: InputStream = resources.openRawResource(resourceId)
            val  text = inputStream.readBytes().toString(Charsets.UTF_8)
            inputStream.close()
            textView.text = text
        }
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        val preference = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val monospaceFont: Typeface? = when (preference.getString(getString(R.string.key_monospace_font), "0")) {
            "0" -> ResourcesCompat.getFont(requireContext(), R.font.font_audiowide)
            "1" -> ResourcesCompat.getFont(requireContext(), R.font.font_fira_code)
            "2" -> ResourcesCompat.getFont(requireContext(), R.font.font_jetbrains_mono)
            "3" -> ResourcesCompat.getFont(requireContext(), R.font.font_noto_sans_mono)
            "4" -> ResourcesCompat.getFont(requireContext(), R.font.font_poppins)
            "5" -> ResourcesCompat.getFont(requireContext(), R.font.font_roboto_mono)
            else -> ResourcesCompat.getFont(requireContext(), R.font.font_audiowide)
        }
        binding.textViewButtonNormalXml.typeface = monospaceFont
        binding.textViewButtonOutlinedXml.typeface = monospaceFont
        binding.textViewButtonElevatedXml.typeface = monospaceFont
        binding.textViewButtonNormalIconXml.typeface = monospaceFont
        binding.textViewButtonOutlinedIconXml.typeface = monospaceFont
        binding.textViewButtonElevatedIconXml.typeface = monospaceFont
        binding.textViewExtendedFloatingButtonPrimaryXml.typeface = monospaceFont
        binding.textViewExtendedFloatingButtonSecondaryXml.typeface = monospaceFont
        binding.textViewExtendedFloatingButtonSurfaceXml.typeface = monospaceFont
        binding.textViewExtendedFloatingButtonTertiaryXml  .typeface = monospaceFont
        binding.textViewExtendedFloatingButtonPrimaryIconXml.typeface = monospaceFont
        binding.textViewExtendedFloatingButtonSecondaryIconXml.typeface = monospaceFont
        binding.textViewExtendedFloatingButtonSurfaceIconXml.typeface = monospaceFont
        binding.textViewExtendedFloatingButtonTertiaryIconXml .typeface = monospaceFont
        binding.textViewFloatingButtonPrimaryXml.typeface = monospaceFont
        binding.textViewFloatingButtonSecondaryXml.typeface = monospaceFont
        binding.textViewFloatingButtonSurfaceXml.typeface = monospaceFont
        binding.textViewFloatingButtonTertiaryXml.typeface = monospaceFont
    }
}