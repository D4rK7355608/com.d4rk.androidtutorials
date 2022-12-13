package com.d4rk.androidtutorials.ui.language
import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityLanguageBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import java.util.Locale
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class LanguageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLanguageBinding
    private lateinit var localeManager: LocaleManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        localeManager = getSystemService(Context.LOCALE_SERVICE) as LocaleManager
        binding.buttonDefault.setOnClickListener {
            localeManager.applicationLocales = LocaleList.getEmptyLocaleList()
        }
        binding.buttonLanguageEn.setOnClickListener {
            localeManager.applicationLocales = LocaleList(Locale.forLanguageTag("en"))
        }
        binding.buttonLanguageRo.setOnClickListener {
            localeManager.applicationLocales = LocaleList(Locale.forLanguageTag("ro"))
        }
        binding.buttonLanguageDe.setOnClickListener {
            localeManager.applicationLocales = LocaleList(Locale.forLanguageTag("de"))
        }
        binding.buttonLanguageEs.setOnClickListener {
            localeManager.applicationLocales = LocaleList(Locale.forLanguageTag("es"))
        }
        binding.buttonLanguageFr.setOnClickListener {
            localeManager.applicationLocales = LocaleList(Locale.forLanguageTag("fr"))
        }
        binding.buttonLanguageHi.setOnClickListener {
            localeManager.applicationLocales = LocaleList(Locale.forLanguageTag("hi"))
        }
        binding.buttonLanguageIn.setOnClickListener {
            localeManager.applicationLocales = LocaleList(Locale.forLanguageTag("in"))
        }
        binding.buttonLanguageIt.setOnClickListener {
            localeManager.applicationLocales = LocaleList(Locale.forLanguageTag("it"))
        }
        binding.buttonLanguageJa.setOnClickListener {
            localeManager.applicationLocales = LocaleList(Locale.forLanguageTag("ja"))
        }
        binding.buttonLanguageRu.setOnClickListener {
            localeManager.applicationLocales = LocaleList(Locale.forLanguageTag("ru"))
        }
        binding.buttonLanguageTr.setOnClickListener {
            localeManager.applicationLocales = LocaleList(Locale.forLanguageTag("tr"))
        }
    }
    override fun onResume() {
        super.onResume()
        val defaultLanguage = String.format(resources.getString(R.string.system_default))
        val language = when (localeManager.applicationLocales.toLanguageTags()) {
            "en" -> "English"
            "ro" -> "Română"
            "de" -> "Deutsch"
            "es" -> "Español"
            "fr" -> "Français"
            "hi" -> "हिन्दी"
            "in" -> "Indonesia"
            "it" -> "Italiano"
            "ja" -> "日本語"
            "ru" -> "Русский"
            "tr" -> "Türkçe"
            else -> defaultLanguage
        }
        val currentLanguage = String.format(resources.getString(R.string.current_language), language)
        binding.textViewCurrentLanguage.text = currentLanguage
    }
}