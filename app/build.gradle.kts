import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.android.application")
    id("kotlin-android")
    //检测当前工程依赖项是否更新：.\gradlew -q dependencyUpdate，输出文件夹：build/dependencyUpdates/report.json
    id("com.github.ben-manes.versions") version "0.42.0"
}

fun String.isNonStable(): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(this)
    return isStable.not()
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        candidate.version.isNonStable()
    }

    checkForGradleUpdate = true
    outputFormatter = "json"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}

@Suppress("UnstableApiUsage") android {
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
    implementation(project(path = ":library"))
}