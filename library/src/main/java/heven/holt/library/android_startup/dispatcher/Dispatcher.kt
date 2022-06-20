package heven.holt.library.android_startup.dispatcher

interface Dispatcher {
    /**
     * 返回true调用主线程上的create函数，否则返回false
     */
    fun callCreateOnMainThread(): Boolean

    /**
     * 返回true阻塞主线程直到启动完成，否则返回false
     * 注意：如果[callCreateOnMainThread]返回true，主线程默认阻塞
     */
    fun waitOnMainThread(): Boolean

    /**
     * 等待依赖项启动完成
     */
    fun toWait()

    /**
     * 当依赖性完成时通知 startup
     */
    fun toNotify()
}