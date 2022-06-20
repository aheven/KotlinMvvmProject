package heven.holt.mvvm.startup

import android.content.Context
import heven.holt.library.android_startup.AndroidStartup

class SampleSyncOneStartup : AndroidStartup<String>() {
    override fun create(context: Context): String {
        return "sync one"
    }

    override fun callCreateOnMainThread(): Boolean = true

    override fun waitOnMainThread(): Boolean = false
}