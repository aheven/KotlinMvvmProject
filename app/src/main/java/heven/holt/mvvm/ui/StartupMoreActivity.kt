package heven.holt.mvvm.ui

import android.content.Context
import android.content.Intent
import android.view.View
import heven.holt.library.ui.base.BaseBindingActivity
import heven.holt.mvvm.databinding.ActivityStartupMoreBinding

class StartupMoreActivity : BaseBindingActivity<ActivityStartupMoreBinding>() {
    companion object {
        fun startAtc(context: Context) {
            val intent = Intent(context, StartupMoreActivity::class.java)
            context.startActivity(intent)
        }
    }

    fun onClick(view: View) {
        StartupCommonActivity.startAtc(view.id)
    }
}