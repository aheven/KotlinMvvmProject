package heven.holt.library.android_startup

import heven.holt.library.android_startup.model.CostTimesModel

interface StartupListener {
    /**
     * 所有启动完成后调用
     * @param totalMainThreadCostTime 主线程总耗时
     * @param costTimesModels 每个启动的耗时时间列表
     */
    fun onCompleted(totalMainThreadCostTime: Long, costTimesModels: List<CostTimesModel>)
}