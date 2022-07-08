@file:Suppress("DEPRECATION")
package com.d4rk.androidtutorials.ui.settings
import android.content.Context
import android.preference.PreferenceManager
import java.util.Locale
object LocaleHelper {
    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
    fun setLocale(context: Context, language: String): Context {
        persist(context, language)
        return updateResources(context, language)
    }
    private fun persist(context: Context, language: String) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putString(SELECTED_LANGUAGE, language)
        editor.apply()
    }
    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }
    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }
}