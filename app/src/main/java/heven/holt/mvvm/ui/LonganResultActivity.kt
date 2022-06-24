package heven.holt.mvvm.ui

import android.view.View
import heven.holt.library.longan.finishWithResult
import heven.holt.library.longan.startActivity
import heven.holt.library.ui.base.BaseBindingActivity
import heven.holt.mvvm.databinding.ActivityLonganResultBinding

class LonganResultActivity : BaseBindingActivity<ActivityLonganResultBinding>() {
    companion object {
        fun startAtc() {
            startActivity<LonganResultActivity>()
        }
    }

    fun clickButton(view: View) {
        finishWithResult("result" to 123456789)
    }
}