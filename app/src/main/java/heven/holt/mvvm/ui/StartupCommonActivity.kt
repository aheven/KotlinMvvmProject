package heven.holt.mvvm.ui

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import heven.holt.library.android_startup.AndroidStartup
import heven.holt.library.android_startup.StartupListener
import heven.holt.library.android_startup.StartupManager
import heven.holt.library.android_startup.manager.StartupCacheManager
import heven.holt.library.android_startup.model.CostTimesModel
import heven.holt.library.android_startup.model.LoggerLevel
import heven.holt.library.android_startup.model.StartupConfig
import heven.holt.library.ui.base.BaseBindingActivity
import heven.holt.mvvm.R
import heven.holt.mvvm.aidl.IMultipleProcessServiceInterface
import heven.holt.mvvm.aidl.IServiceListenerInterface
import heven.holt.mvvm.databinding.ActivityStartupCommonBinding
import heven.holt.mvvm.startup.*
import heven.holt.mvvm.startup.priority.SamplePriorityFirstStartup
import heven.holt.mvvm.startup.priority.SamplePrioritySecondStartup
import heven.holt.mvvm.startup.priority.SamplePriorityThirdStartup
import heven.holt.mvvm.startup.service.MultipleProcessService

class StartupCommonActivity : BaseBindingActivity<ActivityStartupCommonBinding>() {
    companion object {
        fun startAtc(context: Context, @IdRes id: Int) {
            val intent = Intent(context, StartupCommonActivity::class.java)
            intent.putExtra("id", id)
            context.startActivity(intent)
        }
    }

    private var mMultipleProcessService: IMultipleProcessServiceInterface? = null

    private val mMultipleProcessServiceConnection by lazy {
        object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                mMultipleProcessService = IMultipleProcessServiceInterface.Stub.asInterface(service)
                mMultipleProcessService?.addServiceListener(object : IServiceListenerInterface.Stub() {
                    override fun onCompleted(result: String?, totalMainThreadCostTime: Long) {
                        result?.let {
                            Handler(Looper.getMainLooper()).post {
                                showResult(result)
                            }
                        }
                    }
                })
                mMultipleProcessService?.initStartup()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start()
    }

    private fun start() {
        binding.content.text = getString(R.string.sample_startup_not_initialized)

        val list = mutableListOf<AndroidStartup<*>>()
        when (intent.getIntExtra("id", -1)) {
            R.id.sync_and_sync -> {
                list.add(SampleSyncOneStartup())
                list.add(SampleSyncTwoStartup())
            }
            R.id.sync_and_async -> {
                list.add(SampleSyncThreeStartup())
                list.add(SampleAsyncOneStartup())
            }
            R.id.async_and_sync -> {
                list.add(SampleAsyncTwoStartup())
                list.add(SampleSyncFourStartup())
            }
            R.id.async_and_async -> {
                list.add(SampleAsyncFiveStartup())
                list.add(SampleAsyncThreeStartup())
            }
            R.id.async_and_async_await_main_thread -> {
                list.add(SampleAsyncSixStartup())
                list.add(SampleAsyncFourStartup())
            }
            R.id.manual_dispatch -> {
                list.add(SampleManualDispatchStartup())
                list.add(SampleAsyncSevenStartup())
                list.add(SampleSyncFiveStartup())
            }
            R.id.thread_priority -> {
                list.add(SamplePriorityThirdStartup())
                list.add(SamplePrioritySecondStartup())
                list.add(SamplePriorityFirstStartup())
            }
            R.id.multiply_process -> {
                bindService(
                    Intent(this, MultipleProcessService::class.java),
                    mMultipleProcessServiceConnection,
                    Service.BIND_AUTO_CREATE
                )
            }
        }

        val config = StartupConfig.Builder()
            .setLoggerLevel(LoggerLevel.DEBUG)
            .setAwaitTimeout(12000L)
            .setListener(object : StartupListener {
                override fun onCompleted(totalMainThreadCostTime: Long, costTimesModels: List<CostTimesModel>) {
                    showResult(buildString {
                        append("Startup Completed: ")
                        append("\n")
                        append("\n")
                        costTimesModels.forEach {
                            append("\n")
                            append("Startup Name: ${it.name}")
                            append("\n")
                            append("CallOnMainThread: ${it.callOnMainThread}")
                            append("\n")
                            append("WaitOnMainThread: ${it.waitOnMainThread}")
                            append("\n")
                            append("Cost times: ${it.endTime - it.startTime} ms")
                            append("\n")
                        }
                        if (costTimesModels.isEmpty()) {
                            append("result form cache.")
                            append("\n")
                            list.forEach {
                                append("\n")
                                append(
                                    "${it::class.java.simpleName}: ${
                                        StartupCacheManager.instance.obtainInitializedResult<String>(
                                            it::class.java
                                        )
                                    }"
                                )
                                append("\n")
                            }
                        }
                    })
                    Log.d("StartupTrack", "onCompleted: ${costTimesModels.size}")
                }
            })
            .build()

        binding.root.postDelayed({
            StartupManager.Builder()
                .setConfig(config)
                .addAllStartup(list)
                .build(this)
                .start()
                .await()
        }, 16)
    }

    private fun showResult(result: String) {
        binding.content.text = result
    }

    fun get(view: View) {
        start()
    }

    fun clear(view: View) {
        mMultipleProcessService?.clear()
        StartupCacheManager.instance.clear()
        binding.content.text = getString(R.string.clear_cache_success)
    }

    private fun unbindService() {
        mMultipleProcessService?.let {
            unbindService(mMultipleProcessServiceConnection)
        }
    }

    override fun onDestroy() {
        unbindService()
        super.onDestroy()
    }
}