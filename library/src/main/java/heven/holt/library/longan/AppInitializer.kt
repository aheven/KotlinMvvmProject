package heven.holt.library.longan

import android.app.Application
import android.content.Context
import heven.holt.library.android_startup.AndroidStartup

internal class AppInitializer : AndroidStartup<String>() {
    override fun create(context: Context): String {
        application = context as Application
        application.doOnActivityLifecycle(
            onActivityCreated = { activity, _ ->
                activityCache.add(activity)
            },
            onActivityDestroyed = { activity ->
                activityCache.remove(activity)
            }
        )
        return AppInitializer::class.java.simpleName
    }

    override fun callCreateOnMainThread(): Boolean = true

    override fun waitOnMainThread(): Boolean = false
}