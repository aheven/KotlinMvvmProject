package heven.holt.mvvm.startup

import android.content.Context
import android.util.Log
import heven.holt.library.android_startup.AndroidStartup
import heven.holt.library.android_startup.Startup

class SampleThirdStartup : AndroidStartup<Long>() {

    override fun callCreateOnMainThread(): Boolean = false

    override fun waitOnMainThread(): Boolean = false

    override fun create(context: Context): Long {
        Thread.sleep(3000)
        return 10L
    }

    override fun dependenciesByName(): List<String> {
        return listOf(
            "heven.holt.mvvm.startup.SampleFirstStartup",
            "heven.holt.mvvm.startup.SampleSecondStartup"
        )
    }

    override fun onDependenciesCompleted(startup: Startup<*>, result: Any?) {
        Log.d("SampleThirdStartup", "onDependenciesCompleted: ${startup::class.java.simpleName}, $result")
    }
}