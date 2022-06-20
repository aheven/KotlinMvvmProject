package heven.holt.library.android_startup.provider

import heven.holt.library.android_startup.model.StartupConfig

interface StartupProviderConfig {
    fun getConfig(): StartupConfig
}