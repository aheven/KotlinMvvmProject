package heven.holt.mvvm.startup

import android.content.Context
import heven.holt.library.android_startup.AndroidStartup
import heven.holt.library.android_startup.Startup

class SampleSyncTwoStartup : AndroidStartup<String>() {

    private var mResult: String? = null

    override fun create(context: Context): String {
        return "$mResult + sync two"
    }

    override fun callCreateOnMainThread(): Boolean = true

    override fun waitOnMainThread(): Boolean = false

    override fun dependenciesByName(): List<String> {
        return listOf("heven.holt.mvvm.startup.SampleSyncOneStartup")
    }

    override fun onDependenciesCompleted(startup: Startup<*>, result: Any?) {
        this.mResult = result as? String?
    }
}