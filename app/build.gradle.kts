import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(notation = libs.plugins.androidApplication)
    alias(notation = libs.plugins.jetbrainsKotlinAndroid)
    alias(notation = libs.plugins.jetbrainsKotlinParcelize)
    alias(notation = libs.plugins.kotlin.serialization)
    alias(notation = libs.plugins.googlePlayServices)
    alias(notation = libs.plugins.googleFirebase)
    alias(notation = libs.plugins.compose.compiler)
    alias(notation = libs.plugins.devToolsKsp)
    alias(notation = libs.plugins.about.libraries)
}

android {
    compileSdk = 35
    namespace = "com.d4rk.androidtutorials"

    defaultConfig {
        applicationId = "com.d4rk.androidtutorials"
        minSdk = 23
        targetSdk = 35
        versionCode = 115
        versionName = "1.2.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        @Suppress("UnstableApiUsage")
        androidResources.localeFilters += listOf(
            "en" ,
            "bg-rBG" ,
            "de-rDE" ,
            "es-rGQ" ,
            "fr-rFR" ,
            "hi-rIN" ,
            "hu-rHU" ,
            "in-rID" ,
            "it-rIT" ,
            "ja-rJP" ,
            "pl-rPL" ,
            "pt-rBR" ,
            "ro-rRO" ,
            "ru-rRU" ,
            "sv-rSE" ,
            "th-rTH" ,
            "tr-rTR" ,
            "uk-rUA" ,
            "zh-rTW" ,
        )

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isDebuggable = false
        }

        debug {
            isDebuggable = true
        }
    }

    buildTypes.forEach { buildType ->
        val keystoreFile : File = project.rootProject.file("apikeys.properties")
        val properties : Properties = Properties()
        properties.load(keystoreFile.inputStream())
        val apiKey : String = properties.getProperty("API_KEY") ?: ""

        with(buildType) {
            multiDexEnabled = true
            isMinifyEnabled = false
            isShrinkResources = false
            buildConfigField(type = "String" , name = "API_KEY" , value = apiKey)
            proguardFiles(
                getDefaultProguardFile(name = "proguard-android-optimize.txt") ,
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    bundle {
        storeArchive {
            enable = true
        }
    }
}

dependencies {

    // App Core
    implementation(dependencyNotation = "com.github.D4rK7355608:AppToolkit:0.0.53") {
        isTransitive = true
    }

    // Code view
    implementation(dependencyNotation = libs.compose.code.editor)

    // Google
    implementation(dependencyNotation = libs.generativeai)

    // KSP
    ksp(dependencyNotation = libs.androidx.room.compiler)
    implementation(dependencyNotation = libs.androidx.room.ktx)
    implementation(dependencyNotation = libs.androidx.room.runtime)
}