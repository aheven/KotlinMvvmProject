package heven.holt.mvvm.startup

import android.content.Context
import android.util.Log
import heven.holt.library.android_startup.AndroidStartup
import heven.holt.library.android_startup.Startup
import heven.holt.library.android_startup.executor.ExecutorManager
import java.util.concurrent.Executor

class SampleSecondStartup : AndroidStartup<Boolean>() {
    override fun create(context: Context): Boolean {
        Thread.sleep(5000)
        return true
    }

    override fun callCreateOnMainThread(): Boolean = false

    override fun waitOnMainThread(): Boolean = false

    override fun createExecutor(): Executor {
        return ExecutorManager.instance.cpuExecutor
    }

    override fun dependenciesByName(): List<String> {
        return listOf("heven.holt.mvvm.startup.SampleFirstStartup")
    }

    override fun onDependenciesCompleted(startup: Startup<*>, result: Any?) {
        Log.d("SampleSecondStartup", "onDependenciesCompleted: ${startup::class.java.simpleName}, $result")
    }
}