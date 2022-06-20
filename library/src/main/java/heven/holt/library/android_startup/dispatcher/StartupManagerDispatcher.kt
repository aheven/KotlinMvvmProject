package heven.holt.library.android_startup.dispatcher

import android.content.Context
import heven.holt.library.android_startup.Startup
import heven.holt.library.android_startup.StartupListener
import heven.holt.library.android_startup.executor.ExecutorManager
import heven.holt.library.android_startup.extensions.getUniqueKey
import heven.holt.library.android_startup.manager.StartupCacheManager
import heven.holt.library.android_startup.model.StartupSortStore
import heven.holt.library.android_startup.run.StartupRunnable
import heven.holt.library.android_startup.utils.StartupCostTimesUtils
import heven.holt.library.android_startup.utils.StartupLogUtils
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger

internal class StartupManagerDispatcher(
    private val context: Context,
    private val needAwaitCount: AtomicInteger,
    private val awaitCountDownLatch: CountDownLatch?,
    private val startupSize: Int,
    private val listener: StartupListener?
) : ManagerDispatcher{
    private var count: AtomicInteger? = null

    override fun prepare() {
        count = AtomicInteger()
        StartupCostTimesUtils.clear()
    }

    override fun dispatch(startup: Startup<*>, sortStore: StartupSortStore) {
        StartupLogUtils.d { "${startup::class.java.simpleName} being dispatching, onMainThread ${startup.callCreateOnMainThread()}." }

        if (StartupCacheManager.instance.hadInitialized(startup::class.java)) {
            val result = StartupCacheManager.instance.obtainInitializedResult<Any>(startup::class.java)

            StartupLogUtils.d { "${startup::class.java.simpleName} was completed, result from cache." }

            notifyChildren(startup, result, sortStore)
        } else {
            val runnable = StartupRunnable(context, startup, sortStore, this)
            if (!startup.callCreateOnMainThread()) {
                startup.createExecutor().execute(runnable)
            } else {
                runnable.run()
            }
        }
    }

    override fun notifyChildren(dependencyParent: Startup<*>, result: Any?, sortStore: StartupSortStore) {
        // immediately notify main thread,Unblock the main thread.
        if (dependencyParent.waitOnMainThread() && !dependencyParent.callCreateOnMainThread()) {
            needAwaitCount.decrementAndGet()
            awaitCountDownLatch?.countDown()
        }

        sortStore.startupChildrenMap[dependencyParent::class.java.getUniqueKey()]?.forEach {
            sortStore.startupMap[it]?.run {
                onDependenciesCompleted(dependencyParent, result)

                if (dependencyParent.manualDispatch()) {
                    dependencyParent.registerDispatcher(this)
                } else {
                    toNotify()
                }
            }
        }
        val size = count?.incrementAndGet() ?: 0
        if (size == startupSize) {
            StartupCostTimesUtils.printAll()
            listener?.let {
                ExecutorManager.instance.mainExecutor.execute {
                    it.onCompleted(StartupCostTimesUtils.mainThreadTimes, StartupCostTimesUtils.costTimesMap.values.toList())
                }
            }
        }
    }
}