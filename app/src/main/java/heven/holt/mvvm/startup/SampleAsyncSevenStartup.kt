package heven.holt.mvvm.startup

import android.content.Context
import heven.holt.library.android_startup.AndroidStartup
import heven.holt.library.android_startup.Startup

class SampleAsyncSevenStartup : AndroidStartup<String>() {

    private var mResult: String? = null

    override fun create(context: Context): String {
        Thread.sleep(3000)
        return "$mResult + async seven"
    }

    override fun callCreateOnMainThread(): Boolean = false

    override fun waitOnMainThread(): Boolean = false

    override fun dependenciesByName(): List<String> {
        return listOf("heven.holt.mvvm.startup.SampleManualDispatchStartup")
    }

    override fun onDependenciesCompleted(startup: Startup<*>, result: Any?) {
        mResult = result as? String?
    }
}