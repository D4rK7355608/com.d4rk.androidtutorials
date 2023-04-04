package com.d4rk.androidtutorials.ui.settings.language
import android.annotation.SuppressLint
import android.app.Activity
import android.app.LocaleManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityLanguageBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import java.util.Locale
class LanguageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLanguageBinding
    private var localeManager: LocaleManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            localeManager = getSystemService(Context.LOCALE_SERVICE) as LocaleManager
        }
        binding.buttonDefault.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList.getEmptyLocaleList()
            } else {
                setAppLocale(Locale.getDefault().language, this)
            }
        }
        binding.buttonLanguageEn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("en"))
            } else {
                setAppLocale("en", this)
            }
            recreate()
        }
        binding.buttonLanguageRo.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("ro"))
            } else {
                setAppLocale("ro", this)
            }
            recreate()
        }
        binding.buttonLanguageDe.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("de"))
            } else {
                setAppLocale("de", this)
            }
            recreate()
        }
        binding.buttonLanguageEs.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("es"))
            } else {
                setAppLocale("es", this)
            }
            recreate()
        }
        binding.buttonLanguageFr.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("fr"))
            } else {
                setAppLocale("fr", this)
            }
            recreate()
        }
        binding.buttonLanguageHi.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("hi"))
            } else {
                setAppLocale("hi", this)
            }
            recreate()
        }
        binding.buttonLanguageIn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("in"))
            } else {
                setAppLocale("in", this)
            }
            recreate()
        }
        binding.buttonLanguageIt.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("it"))
            } else {
                setAppLocale("it", this)
            }
            recreate()
        }
        binding.buttonLanguageJa.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("ja"))
            } else {
                setAppLocale("ja", this)
            }
            recreate()
        }
        binding.buttonLanguageRu.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("ru"))
            } else {
                setAppLocale("ru", this)
            }
            recreate()
        }
        binding.buttonLanguageTr.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("tr"))
            } else {
                setAppLocale("tr", this)
            }
            recreate()
        }
        binding.buttonLanguageBg.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("bg"))
            } else {
                setAppLocale("bg", this)
            }
            recreate()
        }
        binding.buttonLanguagePl.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("pl"))
            } else {
                setAppLocale("pl", this)
            }
            recreate()
        }
        binding.buttonLanguageUk.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("uk"))
            } else {
                setAppLocale("uk", this)
            }
            recreate()
        }
    }
    @SuppressLint("AppBundleLocaleChanges")
    private fun setAppLocale(language: String, activity: Activity) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        @Suppress("DEPRECATION")
        activity.baseContext.resources.updateConfiguration(config, activity.baseContext.resources.displayMetrics)
    }
    override fun onResume() {
        super.onResume()
        val language = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when (localeManager?.applicationLocales?.toLanguageTags()) {
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
                "sv" -> "Svenska"
                "bg" -> "български"
                "pl" -> "Polski"
                "uk" -> "Ukrainian"
                else -> resources.getString(R.string.system_default)
            }
        } else {
            Locale.getDefault().displayLanguage
        }
        val currentLanguage = resources.getString(R.string.current_language, language)
        binding.textViewCurrentLanguage.text = currentLanguage
    }
}