package heven.holt.library.android_startup.dispatcher

import heven.holt.library.android_startup.Startup
import heven.holt.library.android_startup.model.StartupSortStore

interface ManagerDispatcher {
    /**
     * dispatch 准备
     */
    fun prepare()

    /**
     * dispatch 执行
     */
    fun dispatch(startup: Startup<*>, sortStore: StartupSortStore)

    /**
     * 当依赖项完成时通知孩子
     */
    fun notifyChildren(dependencyParent: Startup<*>, result: Any?, sortStore: StartupSortStore)
}