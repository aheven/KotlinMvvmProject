package heven.holt.mvvm.startup

import android.content.Context
import heven.holt.library.android_startup.AndroidStartup

class SampleAsyncTwoStartup : AndroidStartup<String>() {
    override fun create(context: Context): String {
        Thread.sleep(3000)
        return "async two"
    }

    override fun callCreateOnMainThread(): Boolean = false

    override fun waitOnMainThread(): Boolean = false
}