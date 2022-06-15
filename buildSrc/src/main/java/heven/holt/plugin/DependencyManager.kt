package heven.holt.plugin

object Versions {
    const val core_ktx = "1.7.0"
    const val appcompat = "1.4.1"
    const val material = "1.6.0"
    const val constraintlayout = "2.1.4"
    const val junit = "4.13.2"
    const val test_junit = "1.1.3"
    const val test_espresso = "3.4.0"
    const val fragment_ktx = "1.3.6"
}

object AndroidX {
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val test_junit = "androidx.test.ext:junit:${Versions.test_junit}"
    const val test_espresso = "androidx.test.espresso:espresso-core:${Versions.test_espresso}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}"
}

object Android {
    const val meteria = "com.google.android.material:material:${Versions.material}"
}

object Depends {
    const val junit = "junit:junit:${Versions.junit}"
}