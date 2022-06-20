package heven.holt.library.android_startup.executor

import java.util.concurrent.Executor

interface StartupExecutor {
    fun createExecutor(): Executor
}