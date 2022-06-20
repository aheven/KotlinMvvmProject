package heven.holt.library.android_startup

import android.content.Context
import android.os.Looper
import android.os.Trace
import heven.holt.library.android_startup.annotation.MultipleProcess
import heven.holt.library.android_startup.dispatcher.StartupManagerDispatcher
import heven.holt.library.android_startup.exception.StartupException
import heven.holt.library.android_startup.manager.StartupCacheManager
import heven.holt.library.android_startup.model.LoggerLevel
import heven.holt.library.android_startup.model.StartupConfig
import heven.holt.library.android_startup.model.StartupSortStore
import heven.holt.library.android_startup.sort.TopologySort
import heven.holt.library.android_startup.utils.ProcessUtils
import heven.holt.library.android_startup.utils.StartupCostTimesUtils
import heven.holt.library.android_startup.utils.StartupLogUtils
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class StartupManager private constructor(
    private val context: Context,
    private val startupList: List<AndroidStartup<*>>,
    private val needAwaitCount: AtomicInteger,
    private val config: StartupConfig
) {

    private var mAwaitCountDownLatch: CountDownLatch? = null

    companion object {
        const val AWAIT_TIMEOUT = 10000L
    }

    init {
        StartupCacheManager.instance.saveConfig(config)
        StartupLogUtils.level = config.loggerLevel
    }

    fun start() = apply {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw StartupException("start method must be call in MainThread.")
        }

        if (mAwaitCountDownLatch != null) {
            throw StartupException("start method repeated call.")
        }
        mAwaitCountDownLatch = CountDownLatch(needAwaitCount.get())

        if (startupList.isEmpty()) {
            StartupLogUtils.e { "startupList is empty in the current process." }
            return@apply
        }

        Trace.beginSection(StartupManager::class.java.simpleName)
        StartupCostTimesUtils.startTime = System.nanoTime()

        TopologySort.sort(startupList).run {
            mDefaultManagerDispatcher.prepare()
            execute(this)
        }

        if (needAwaitCount.get() <= 0) {
            StartupCostTimesUtils.endTime = System.nanoTime()
            Trace.endSection()
        }
    }

    private fun execute(sortStore: StartupSortStore) {
        sortStore.result.forEach { mDefaultManagerDispatcher.dispatch(it, sortStore) }
    }

    private val mDefaultManagerDispatcher by lazy {
        StartupManagerDispatcher(context, needAwaitCount, mAwaitCountDownLatch, startupList.size, config.listener)
    }

    /**
     * 等待启动完成，阻塞主线程
     */
    fun await() {
        if (mAwaitCountDownLatch == null) {
            throw StartupException("must be call start method before call await method.")
        }

        val count = needAwaitCount.get()
        try {
            mAwaitCountDownLatch?.await(config.awaitTimeout, TimeUnit.MILLISECONDS)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        if (count > 0) {
            StartupCostTimesUtils.endTime = System.nanoTime()
            Trace.endSection()
        }
    }

    class Builder {
        private var mStartupList = mutableListOf<AndroidStartup<*>>()
        private var mNeedAwaitCount = AtomicInteger()
        private var mLoggerLevel = LoggerLevel.NONE
        private var mAwaitTimeout = AWAIT_TIMEOUT
        private var mConfig: StartupConfig? = null

        fun addStartup(startup: AndroidStartup<*>) = apply {
            mStartupList.add(startup)
        }

        fun addAllStartup(list: List<AndroidStartup<*>>) = apply {
            list.forEach {
                addStartup(it)
            }
        }

        fun setConfig(config: StartupConfig?) = apply {
            mConfig = config
        }

        @Deprecated("Use setConfig() instead.")
        fun setLoggerLevel(level: LoggerLevel) = apply {
            mLoggerLevel = level
        }

        @Deprecated("Use setConfig() instead.")
        fun setAwaitTimeout(timeoutMilliSeconds: Long) = apply {
            mAwaitTimeout = timeoutMilliSeconds
        }

        fun build(context: Context): StartupManager {
            val realStartupList = mutableListOf<AndroidStartup<*>>()
            mStartupList.forEach {
                val process = it::class.java.getAnnotation(MultipleProcess::class.java)?.process ?: arrayOf()
                if (process.isEmpty() || ProcessUtils.isMultipleProcess(context, process)) {
                    realStartupList.add(it)
                    if (it.waitOnMainThread() && !it.callCreateOnMainThread()) {
                        mNeedAwaitCount.incrementAndGet()
                    }
                }
            }

            return StartupManager(
                context,
                realStartupList,
                mNeedAwaitCount,
                mConfig ?: StartupConfig.Builder()
                    .setLoggerLevel(mLoggerLevel)
                    .setAwaitTimeout(mAwaitTimeout)
                    .build()
            )
        }
    }
}
