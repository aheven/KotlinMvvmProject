package heven.holt.library.android_startup.run

import android.content.Context
import android.os.Process
import android.os.Trace
import heven.holt.library.android_startup.Startup
import heven.holt.library.android_startup.annotation.ThreadPriority
import heven.holt.library.android_startup.dispatcher.ManagerDispatcher
import heven.holt.library.android_startup.manager.StartupCacheManager
import heven.holt.library.android_startup.model.ResultModel
import heven.holt.library.android_startup.model.StartupSortStore
import heven.holt.library.android_startup.utils.StartupCostTimesUtils
import heven.holt.library.android_startup.utils.StartupLogUtils

internal class StartupRunnable(
    private val context: Context,
    private val startup: Startup<*>,
    private val sortStore: StartupSortStore,
    private val dispatcher: ManagerDispatcher
) : Runnable {
    override fun run() {
        Process.setThreadPriority(startup::class.java.getAnnotation(ThreadPriority::class.java)?.priority ?: Process.THREAD_PRIORITY_DEFAULT)
        startup.toWait()
        StartupLogUtils.d { "${startup::class.java.simpleName} being create." }

        Trace.beginSection(startup::class.java.simpleName)
        StartupCostTimesUtils.recordStart { Triple(startup::class.java, startup.callCreateOnMainThread(), startup.waitOnMainThread()) }
        val result = startup.create(context)
        StartupCostTimesUtils.recordEnd { startup::class.java }
        Trace.endSection()

        // To save result of initialized component.
        StartupCacheManager.instance.saveInitializedComponent(startup::class.java, ResultModel(result))
        StartupLogUtils.d { "${startup::class.java.simpleName} was completed." }

        dispatcher.notifyChildren(startup, result, sortStore)
    }
}