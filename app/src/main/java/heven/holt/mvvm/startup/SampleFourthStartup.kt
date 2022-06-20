package heven.holt.mvvm.startup

import android.content.Context
import heven.holt.library.android_startup.AndroidStartup

class SampleFourthStartup : AndroidStartup<Any>() {

    override fun callCreateOnMainThread(): Boolean = false

    override fun waitOnMainThread(): Boolean = false

    override fun create(context: Context): Any? {
        Thread.sleep(100)
        return null
    }

    override fun dependenciesByName(): List<String> {
        return listOf(
            "heven.holt.mvvm.startup.SampleFirstStartup",
            "heven.holt.mvvm.startup.SampleSecondStartup",
            "heven.holt.mvvm.startup.SampleThirdStartup"
        )
    }
}