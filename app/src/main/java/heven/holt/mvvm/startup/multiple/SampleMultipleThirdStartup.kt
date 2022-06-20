package heven.holt.mvvm.startup.multiple

import android.content.Context
import heven.holt.library.android_startup.AndroidStartup
import heven.holt.library.android_startup.annotation.MultipleProcess

@MultipleProcess(":multiple.test")
class SampleMultipleThirdStartup : AndroidStartup<String>() {

    override fun create(context: Context): String? {
        return SampleMultipleThirdStartup::class.java.name
    }

    override fun callCreateOnMainThread(): Boolean = false

    override fun waitOnMainThread(): Boolean = false

}