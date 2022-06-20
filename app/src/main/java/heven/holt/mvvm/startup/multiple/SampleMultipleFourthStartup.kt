package heven.holt.mvvm.startup.multiple

import android.content.Context
import heven.holt.library.android_startup.AndroidStartup
import heven.holt.library.android_startup.annotation.MultipleProcess

@MultipleProcess(":multiple.process.service", ":multiple.test")
class SampleMultipleFourthStartup : AndroidStartup<String>() {

    override fun create(context: Context): String? {
        Thread.sleep(1000)
        return SampleMultipleFourthStartup::class.java.simpleName
    }

    override fun callCreateOnMainThread(): Boolean = false

    override fun waitOnMainThread(): Boolean = false
}