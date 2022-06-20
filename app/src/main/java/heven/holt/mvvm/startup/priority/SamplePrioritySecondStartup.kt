package heven.holt.mvvm.startup.priority

import android.content.Context
import heven.holt.library.android_startup.AndroidStartup

class SamplePrioritySecondStartup : AndroidStartup<String>() {

    override fun create(context: Context): String? {
        buildString {
            repeat(1000000) {
                append("$it")
            }
        }
        return SamplePrioritySecondStartup::class.java.simpleName
    }

    override fun callCreateOnMainThread(): Boolean = false

    override fun waitOnMainThread(): Boolean = false

}