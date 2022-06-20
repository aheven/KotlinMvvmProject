package heven.holt.mvvm

import android.view.View
import heven.holt.library.ui.base.BaseBindingActivity
import heven.holt.mvvm.databinding.ActivityMainBinding
import heven.holt.mvvm.ui.StartupActivity
import heven.holt.mvvm.ui.ViewBindingKtxActivity

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    fun intentViewBindingKtx(view: View) {
        ViewBindingKtxActivity.startAtc(view.context)
    }

    fun intentStartupManager(view: View) {
        StartupActivity.startAtc(view.context)
    }
}