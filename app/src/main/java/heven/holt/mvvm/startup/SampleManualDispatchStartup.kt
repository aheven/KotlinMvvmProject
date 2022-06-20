package heven.holt.mvvm.startup

import android.content.Context
import heven.holt.library.android_startup.AndroidStartup

class SampleManualDispatchStartup : AndroidStartup<String>() {

    override fun create(context: Context): String {
        Thread {
            Thread.sleep(2000)
            // manual dispatch
            onDispatch()
        }.start()
        return "manual dispatch"
    }

    override fun callCreateOnMainThread(): Boolean = true

    override fun waitOnMainThread(): Boolean = false

    override fun manualDispatch(): Boolean = true

}