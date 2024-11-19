plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.googlePlayServices) apply false
    alias(libs.plugins.googleFirebase) apply false
    alias(libs.plugins.devToolsKsp) apply false
    alias(libs.plugins.about.libraries) apply true
}