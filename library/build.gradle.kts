@file:Suppress("UnstableApiUsage")

import heven.holt.plugin.*

plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = BuildConfig.compileSdkVersion

    defaultConfig {
        minSdk = BuildConfig.minSdkVersion
        targetSdk = BuildConfig.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(AndroidX.core_ktx)
    api(AndroidX.appcompat)
    api(Android.meteria)
    api(AndroidX.constraintlayout)
    api(AndroidX.fragment_ktx)

    testApi(Depends.junit)
    androidTestApi(AndroidX.test_junit)
    androidTestApi(AndroidX.test_espresso)
}

