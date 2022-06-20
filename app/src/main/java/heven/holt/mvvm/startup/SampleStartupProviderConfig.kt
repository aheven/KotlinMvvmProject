package heven.holt.mvvm.startup

import android.util.Log
import heven.holt.library.android_startup.StartupListener
import heven.holt.library.android_startup.model.CostTimesModel
import heven.holt.library.android_startup.model.LoggerLevel
import heven.holt.library.android_startup.model.StartupConfig
import heven.holt.library.android_startup.provider.StartupProviderConfig

class SampleStartupProviderConfig(
    private val sampleStartupProviderListener: SampleStartupProviderListener?
) : StartupProviderConfig {

    constructor() : this(null)

    fun interface SampleStartupProviderListener {
        fun onCompleted(costTimesModels: List<CostTimesModel>)
    }

    override fun getConfig(): StartupConfig =
        StartupConfig.Builder()
            .setLoggerLevel(LoggerLevel.DEBUG) // default LoggerLevel.NONE
            .setAwaitTimeout(12000L) // default 10000L
            .setOpenStatistics(true) // default true
            .setListener(object : StartupListener {
                override fun onCompleted(totalMainThreadCostTime: Long, costTimesModels: List<CostTimesModel>) {
                    // can to do cost time statistics.
                    Log.d("StartupTrack", "onCompleted: ${costTimesModels.size}")
                    sampleStartupProviderListener?.onCompleted(costTimesModels)
                }
            })
            .build()
}