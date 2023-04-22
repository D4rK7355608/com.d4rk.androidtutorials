package com.d4rk.androidtutorials.ui.settings.language
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
                setAppLocale( this, Locale.getDefault().language)
            }
        }
        binding.buttonLanguageEn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("en"))
            } else {
                setAppLocale( this, "en")
            }
            recreate()
        }
        binding.buttonLanguageRo.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("ro"))
            } else {
                setAppLocale( this, "ro")
            }
            recreate()
        }
        binding.buttonLanguageDe.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("de"))
            } else {
                setAppLocale( this, "de")
            }
            recreate()
        }
        binding.buttonLanguageEs.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("es"))
            } else {
                setAppLocale( this, "es")
            }
            recreate()
        }
        binding.buttonLanguageFr.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("fr"))
            } else {
                setAppLocale( this, "fr")
            }
            recreate()
        }
        binding.buttonLanguageHi.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("hi"))
            } else {
                setAppLocale( this, "hi")
            }
            recreate()
        }
        binding.buttonLanguageIn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("in"))
            } else {
                setAppLocale( this, "in")
            }
            recreate()
        }
        binding.buttonLanguageIt.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("it"))
            } else {
                setAppLocale( this, "it")
            }
            recreate()
        }
        binding.buttonLanguageJa.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("ja"))
            } else {
                setAppLocale( this, "ja")
            }
            recreate()
        }
        binding.buttonLanguageRu.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("ru"))
            } else {
                setAppLocale( this, "ru")
            }
            recreate()
        }
        binding.buttonLanguageTr.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("tr"))
            } else {
                setAppLocale( this, "tr")
            }
            recreate()
        }
        binding.buttonLanguageBg.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("bg"))
            } else {
                setAppLocale( this, "bg")
            }
            recreate()
        }
        binding.buttonLanguagePl.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("pl"))
            } else {
                setAppLocale( this, "pl")
            }
            recreate()
        }
        binding.buttonLanguageUk.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("uk"))
            } else {
                setAppLocale( this, "uk")
            }
            recreate()
        }
    }
    @Suppress("DEPRECATION")
    private fun setAppLocale(context: Context, language: String) {
        val config = Configuration()
        config.setLocale(Locale.Builder().setLanguageTag(language).build())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val localeManager = context.getSystemService(Context.LOCALE_SERVICE) as LocaleManager
            localeManager.applicationLocales = LocaleList(Locale.forLanguageTag(language))
        }
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
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