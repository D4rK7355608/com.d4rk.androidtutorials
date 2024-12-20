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
        versionCode = 92
        versionName = "1.1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        resourceConfigurations += listOf(
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
        val keystoreFile = project.rootProject.file("apikeys.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())
        val apiKey = properties.getProperty("API_KEY") ?: ""

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

    //AndroidX
    implementation(dependencyNotation = libs.androidx.core.ktx)
    implementation(dependencyNotation = libs.androidx.appcompat)
    implementation(dependencyNotation = libs.androidx.core.splashscreen)
    implementation(dependencyNotation = libs.androidx.multidex)
    implementation(dependencyNotation = libs.androidx.work.runtime.ktx)

    // Compose
    implementation(dependencyNotation = platform(libs.androidx.compose.bom))
    implementation(dependencyNotation = libs.androidx.ui)
    implementation(dependencyNotation = libs.androidx.activity.compose)
    implementation(dependencyNotation = libs.androidx.ui.graphics)
    implementation(dependencyNotation = libs.androidx.compose.runtime)
    implementation(dependencyNotation = libs.androidx.runtime.livedata)
    implementation(dependencyNotation = libs.androidx.ui.tooling.preview)
    implementation(dependencyNotation = libs.androidx.material3)
    implementation(dependencyNotation = libs.androidx.material.icons.extended)
    implementation(dependencyNotation = libs.datastore.preferences)
    implementation(dependencyNotation = libs.androidx.datastore.preferences)
    implementation(dependencyNotation = libs.androidx.foundation)
    implementation(dependencyNotation = libs.androidx.navigation.compose)

    // Code view
    implementation(dependencyNotation = libs.compose.code.editor)

    // Firebase
    implementation(dependencyNotation = platform(libs.firebase.bom))
    implementation(dependencyNotation = libs.firebase.analytics.ktx)
    implementation(dependencyNotation = libs.firebase.crashlytics.ktx)
    implementation(dependencyNotation = libs.firebase.perf)

    // Google
    implementation(dependencyNotation = libs.play.services.ads)
    implementation(dependencyNotation = libs.billing)
    implementation(dependencyNotation = libs.material)
    implementation(dependencyNotation = libs.app.update.ktx)
    implementation(dependencyNotation = libs.review.ktx)
    implementation(dependencyNotation = libs.generativeai)

    // Images
    implementation(dependencyNotation = libs.coil.compose)
    implementation(dependencyNotation = libs.coil.gif)
    implementation(dependencyNotation = libs.coil.network.okhttp)

    // Kotlin
    implementation(dependencyNotation = libs.kotlinx.coroutines.android)
    implementation(dependencyNotation = libs.kotlinx.serialization.json)

    // Ktor
    implementation(dependencyNotation = platform(libs.ktor.bom))
    implementation(dependencyNotation = libs.ktor.client.android)
    implementation(dependencyNotation = libs.ktor.client.serialization)
    implementation(dependencyNotation = libs.ktor.client.logging)
    implementation(dependencyNotation = libs.ktor.client.content.negotiation)
    implementation(dependencyNotation = libs.ktor.serialization.kotlinx.json)

    // KSP
    ksp(dependencyNotation = libs.androidx.room.compiler)
    implementation(dependencyNotation = libs.androidx.room.ktx)
    implementation(dependencyNotation = libs.androidx.room.runtime)

    // Lifecycle
    implementation(dependencyNotation = libs.androidx.lifecycle.runtime.ktx)
    implementation(dependencyNotation = libs.androidx.lifecycle.livedata.ktx)
    implementation(dependencyNotation = libs.androidx.lifecycle.process)
    implementation(dependencyNotation = libs.androidx.lifecycle.viewmodel.ktx)
    implementation(dependencyNotation = libs.androidx.lifecycle.viewmodel.compose)
    implementation(dependencyNotation = libs.androidx.lifecycle.runtime.compose)

    // About
    implementation(dependencyNotation = libs.aboutlibraries)
    implementation(dependencyNotation = libs.core)
}