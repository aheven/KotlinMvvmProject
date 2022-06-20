package heven.holt.mvvm.startup

import android.content.Context
import heven.holt.library.android_startup.AndroidStartup

class SampleSyncThreeStartup : AndroidStartup<String>() {
    override fun create(context: Context): String {
        return "sync three"
    }

    override fun callCreateOnMainThread(): Boolean = true

    override fun waitOnMainThread(): Boolean = false
}