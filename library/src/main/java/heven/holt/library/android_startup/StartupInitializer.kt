package heven.holt.library.android_startup

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Trace
import heven.holt.library.R
import heven.holt.library.android_startup.exception.StartupException
import heven.holt.library.android_startup.extensions.getUniqueKey
import heven.holt.library.android_startup.manager.StartupCacheManager
import heven.holt.library.android_startup.model.StartupProviderStore
import heven.holt.library.android_startup.provider.StartupProviderConfig

class StartupInitializer {
    companion object {
        @JvmStatic
        val instance by lazy { StartupInitializer() }
    }

    internal fun discoverAndInitialize(context: Context, providerName: String): StartupProviderStore {
        Trace.beginSection(StartupInitializer::class.java.simpleName)

        val result = mutableListOf<AndroidStartup<*>>()
        val initialize = mutableListOf<String>()
        val initialized = mutableListOf<String>()
        var config: StartupProviderConfig? = null

        try {
            val provider = ComponentName(context.packageName, providerName)
            val providerInfo = context.packageManager.getProviderInfo(provider, PackageManager.GET_META_DATA)
            val startup = context.getString(R.string.android_startup)
            val providerConfig = context.getString(R.string.android_startup_provider_config)
            providerInfo.metaData?.let { metaData ->
                metaData.keySet().forEach { key ->
                    val value = metaData[key]
                    val clazz = Class.forName(key)
                    if (startup == value) {
                        if (AndroidStartup::class.java.isAssignableFrom(clazz)) {
                            doInitialize(
                                (clazz.getDeclaredConstructor().newInstance() as AndroidStartup<*>),
                                result,
                                initialize,
                                initialized
                            )
                        }
                    } else if (providerConfig == value) {
                        if (StartupProviderConfig::class.java.isAssignableFrom(clazz)) {
                            config = clazz.getDeclaredConstructor().newInstance() as? StartupProviderConfig
                            StartupCacheManager.instance.saveConfig(config?.getConfig())
                        }
                    }
                }
            }
        } catch (t: Throwable) {
            throw StartupException(t)
        }

        Trace.endSection()

        return StartupProviderStore(result, config)
    }

    private fun doInitialize(
        startup: AndroidStartup<*>,
        result: MutableList<AndroidStartup<*>>,
        initialize: MutableList<String>,
        initialized: MutableList<String>
    ) {
        try {
            val uniqueKey = startup::class.java.getUniqueKey()
            if (initialize.contains(uniqueKey)) {
                throw IllegalStateException("have circle dependencies.")
            }
            if (!initialized.contains(uniqueKey)) {
                initialize.add(uniqueKey)
                result.add(startup)
                startup.dependenciesByName()?.forEach {
                    val clazz = Class.forName(it)
                    doInitialize(
                        clazz.getDeclaredConstructor().newInstance() as AndroidStartup<*>,
                        result,
                        initialize,
                        initialized
                    )
                }
                initialize.remove(uniqueKey)
                initialized.add(uniqueKey)
            }
        } catch (t: Throwable) {
            throw StartupException(t)
        }
    }
}