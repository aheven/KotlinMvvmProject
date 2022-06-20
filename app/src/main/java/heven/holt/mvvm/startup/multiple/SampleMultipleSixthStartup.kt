package heven.holt.mvvm.startup.multiple

import android.content.Context
import heven.holt.library.android_startup.AndroidStartup
import heven.holt.library.android_startup.annotation.MultipleProcess

@MultipleProcess(":multiple.process.service")
class SampleMultipleSixthStartup : AndroidStartup<String>() {

    override fun create(context: Context): String? {
        return SampleMultipleSixthStartup::class.java.simpleName
    }

    override fun callCreateOnMainThread(): Boolean = false

    override fun waitOnMainThread(): Boolean = true

    override fun dependenciesByName(): List<String> {
        return listOf("heven.holt.mvvm.startup.multiple.SampleMultipleFifthStartup")
    }

}