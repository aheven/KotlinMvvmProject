package heven.holt.library.android_startup.model

import heven.holt.library.android_startup.AndroidStartup
import heven.holt.library.android_startup.provider.StartupProviderConfig

data class StartupProviderStore(
    val result: List<AndroidStartup<*>>,
    val config: StartupProviderConfig?
)
