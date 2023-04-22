package com.d4rk.androidtutorials.ui.settings.language
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityLanguageBinding
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import java.util.Locale
class LanguageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLanguageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        val currentLocaleName = if (!AppCompatDelegate.getApplicationLocales().isEmpty) {
            AppCompatDelegate.getApplicationLocales()[0]?.displayName
        } else {
            Locale.getDefault().displayName
        }
        val currentLanguage = resources.getString(R.string.current_language, currentLocaleName )
        binding.textViewCurrentLanguage.text = currentLanguage
        binding.buttonDefault.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.getEmptyLocaleList())
        }
        binding.buttonLanguageEn.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("en"))
        }
        binding.buttonLanguageRo.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("ro"))
        }
        binding.buttonLanguageDe.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("de"))
        }
        binding.buttonLanguageEs.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("es"))
        }
        binding.buttonLanguageFr.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("fr"))
        }
        binding.buttonLanguageHi.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("hi"))
        }
        binding.buttonLanguageIn.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("in"))
        }
        binding.buttonLanguageIt.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("it"))
        }
        binding.buttonLanguageJa.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("ja"))
        }
        binding.buttonLanguageRu.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("ru"))
        }
        binding.buttonLanguageTr.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("tr"))
        }
        binding.buttonLanguageBg.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("bg"))
        }
        binding.buttonLanguagePl.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("pl"))
        }
        binding.buttonLanguageUk.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("uk"))
        }
    }
}