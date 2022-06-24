package heven.holt.mvvm.ui

import android.view.View
import heven.holt.library.android_startup.StartupManager
import heven.holt.library.android_startup.manager.StartupCacheManager
import heven.holt.library.android_startup.model.CostTimesModel
import heven.holt.library.longan.startActivity
import heven.holt.library.ui.base.BaseBindingActivity
import heven.holt.mvvm.R
import heven.holt.mvvm.databinding.ActivityStartupBinding
import heven.holt.mvvm.startup.SampleFirstStartup
import heven.holt.mvvm.startup.SampleSecondStartup
import heven.holt.mvvm.startup.SampleStartupProviderConfig

class StartupActivity : BaseBindingActivity<ActivityStartupBinding>(),
    SampleStartupProviderConfig.SampleStartupProviderListener {
    companion object {
        fun startAtc() {
            startActivity<StartupActivity>()
        }
    }

    fun get(view: View) {
        if (StartupCacheManager.instance.hadInitialized(SampleSecondStartup::class.java)) {
            binding.content.text = getString(
                R.string.sample_second_startup_result_from_cache,
                StartupCacheManager.instance.obtainInitializedResult<Boolean>(SampleSecondStartup::class.java)
            )
        } else {
            binding.content.text = getString(R.string.sample_second_startup_not_initialized)
            // 因为 SampleSecondStartup 需要阻塞在主线程上。
            // 为了显示初始化提示，延迟一帧时间。
            view.postDelayed({
                StartupManager.Builder()
                    .setConfig(SampleStartupProviderConfig(this).getConfig())
                    .addStartup(SampleFirstStartup())
                    .addStartup(SampleSecondStartup())
                    .build(this)
                    .start()
                    .await()
            }, 16)
        }
    }

    fun clear(view: View) {
        StartupCacheManager.instance.remove(SampleSecondStartup::class.java)
        binding.content.text = getString(R.string.clear_cache_success)
    }

    fun more(view: View) {
        StartupMoreActivity.startAtc(view.context)
    }

    override fun onCompleted(costTimesModels: List<CostTimesModel>) {
        binding.content.text = buildString {
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
        }
    }
}