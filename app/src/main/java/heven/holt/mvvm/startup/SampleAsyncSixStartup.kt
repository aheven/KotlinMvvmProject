package heven.holt.mvvm.startup

import android.content.Context
import heven.holt.library.android_startup.AndroidStartup

class SampleAsyncSixStartup: AndroidStartup<String>() {

    override fun callCreateOnMainThread(): Boolean = false

    override fun create(context: Context): String {
        Thread.sleep(2000)
        return "async six"
    }

    override fun waitOnMainThread(): Boolean = false

}