package heven.holt.mvvm.startup.priority

import android.content.Context
import android.os.Process
import heven.holt.library.android_startup.AndroidStartup
import heven.holt.library.android_startup.annotation.ThreadPriority

@ThreadPriority(Process.THREAD_PRIORITY_URGENT_AUDIO)
class SamplePriorityFirstStartup : AndroidStartup<String>() {

    override fun create(context: Context): String? {
        buildString {
            repeat(1000000) {
                append("$it")
            }
        }
        return SamplePriorityFirstStartup::class.java.simpleName
    }

    override fun callCreateOnMainThread(): Boolean = false

    override fun waitOnMainThread(): Boolean = false

}