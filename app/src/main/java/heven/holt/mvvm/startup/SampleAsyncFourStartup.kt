package heven.holt.mvvm.startup

import android.content.Context
import heven.holt.library.android_startup.AndroidStartup
import heven.holt.library.android_startup.Startup

class SampleAsyncFourStartup : AndroidStartup<String>() {

    private var mResult: String? = null

    override fun callCreateOnMainThread(): Boolean = false

    override fun create(context: Context): String {
        Thread.sleep(1000)
        return "$mResult + async four"
    }

    override fun waitOnMainThread(): Boolean = true

    override fun dependenciesByName(): List<String> {
        return listOf("heven.holt.mvvm.startup.SampleAsyncSixStartup")
    }

    override fun onDependenciesCompleted(startup: Startup<*>, result: Any?) {
        mResult = result as? String?
    }
}