package heven.holt.library.android_startup.manager

import heven.holt.library.android_startup.Startup
import heven.holt.library.android_startup.model.ResultModel
import heven.holt.library.android_startup.model.StartupConfig
import java.util.concurrent.ConcurrentHashMap

class StartupCacheManager {

    /**
     * 保存初始化组件结果
     */
    private val mInitializedComponents = ConcurrentHashMap<Class<out Startup<*>>, ResultModel<*>>()
    var initializedConfig: StartupConfig? = null
        private set

    companion object {
        @JvmStatic
        val instance by lazy { StartupCacheManager() }
    }

    /**
     * 保存初始化组件结果
     */
    internal fun saveInitializedComponent(zClass: Class<out Startup<*>>, result: ResultModel<*>) {
        mInitializedComponents[zClass] = result
    }

    /**
     * 校验是否初始化
     */
    fun hadInitialized(zClass: Class<out Startup<*>>): Boolean = mInitializedComponents.containsKey(zClass)

    @Suppress("UNCHECKED_CAST")
    fun <T> obtainInitializedResult(zClass: Class<out Startup<*>>): T? = mInitializedComponents[zClass]?.result as? T?

    fun remove(zClass: Class<out Startup<*>>) {
        mInitializedComponents.remove(zClass)
    }

    fun clear() {
        mInitializedComponents.clear()
    }

    /**
     * 保存初始化配置
     */
    internal fun saveConfig(config: StartupConfig?) {
        initializedConfig = config
    }
}