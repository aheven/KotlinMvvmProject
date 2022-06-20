package heven.holt.library.android_startup

import heven.holt.library.android_startup.dispatcher.Dispatcher
import heven.holt.library.android_startup.executor.ExecutorManager
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor

abstract class AndroidStartup<T> : Startup<T> {
    private val mWaitCountDown by lazy { CountDownLatch(getDependenciesCount()) }
    private val mObservers by lazy { mutableListOf<Dispatcher>() }

    override fun toWait() {
        try {
            mWaitCountDown.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    override fun toNotify() {
        mWaitCountDown.countDown()
    }

    override fun createExecutor(): Executor = ExecutorManager.instance.ioExecutor

    override fun dependenciesByName(): List<String>? {
        return null
    }

    override fun getDependenciesCount(): Int {
        return dependenciesByName()?.size ?: 0
    }

    override fun onDependenciesCompleted(startup: Startup<*>, result: Any?) {}

    override fun manualDispatch(): Boolean = false

    override fun registerDispatcher(dispatcher: Dispatcher) {
        mObservers.add(dispatcher)
    }

    override fun onDispatch() {
        mObservers.forEach { it.toNotify() }
    }
}