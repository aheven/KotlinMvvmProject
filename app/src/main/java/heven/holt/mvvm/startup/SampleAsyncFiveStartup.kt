package heven.holt.mvvm.startup

import android.content.Context
import heven.holt.library.android_startup.AndroidStartup

class SampleAsyncFiveStartup : AndroidStartup<String>() {
    override fun callCreateOnMainThread(): Boolean = false

    override fun create(context: Context): String {
        Thread.sleep(1000)
        return "async five"
    }

    override fun waitOnMainThread(): Boolean = false
}