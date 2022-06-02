import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import heven.holt.plugin.Android
import heven.holt.plugin.AndroidX
import heven.holt.plugin.Depends

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = BuildConfig.compileSdkVersion

    defaultConfig {
        applicationId = "heven.holt.mvvm"
        minSdk = BuildConfig.minSdkVersion
        targetSdk = BuildConfig.targetSdkVersion
        versionCode = BuildConfig.versionCode
        versionName = BuildConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

android.applicationVariants.all {
    outputs.all {
        if (this is ApkVariantOutputImpl) {
            val apkName = "${flavorName}_$applicationId"
            val fileName = "${buildType.name}_${apkName}_$versionName.apk"
            outputFileName = fileName
        }
    }
}

dependencies {
    implementation(AndroidX.core_ktx)
    implementation(AndroidX.appcompat)
    implementation(Android.meteria)
    implementation(AndroidX.constraintlayout)

    testImplementation(Depends.junit)
    androidTestImplementation(AndroidX.test_junit)
    androidTestImplementation(AndroidX.test_espresso)
}