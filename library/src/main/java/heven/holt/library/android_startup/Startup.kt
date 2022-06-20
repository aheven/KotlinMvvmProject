package heven.holt.library.android_startup

import android.content.Context
import heven.holt.library.android_startup.dispatcher.Dispatcher
import heven.holt.library.android_startup.executor.StartupExecutor

interface Startup<T> : Dispatcher, StartupExecutor {
    /**
     * 包含初始化组件所需的所有操作
     */
    fun create(context: Context): T?

    /**
     * 返回初始化程序所依赖的其它[Startup]类名称的列表
     */
    fun dependenciesByName(): List<String>?

    /**
     * 返回初始化程序所依赖的其它[Startup]类数量
     */
    fun getDependenciesCount(): Int

    /**
     * 每当有依赖完成时调用
     * @param [startup] 依赖项 [startup]
     * @param [result] 依赖返回结果
     */
    fun onDependenciesCompleted(startup: Startup<*>, result: Any?)

    /**
     * 返回true手动分发，但必须调用[onDispatch]，以通知子项依赖项[Startup]已经完成
     */
    fun manualDispatch(): Boolean

    /**
     * 当[manualDispatch]返回true时注册调度程序
     */
    fun registerDispatcher(dispatcher: Dispatcher)

    /**
     * 当[manualDispatch]返回true时开始分发
     */
    fun onDispatch()
}