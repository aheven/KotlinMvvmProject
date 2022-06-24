package heven.holt.mvvm.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import heven.holt.library.longan.TAG
import heven.holt.library.longan.immerseStatusBar
import heven.holt.library.longan.launchWifiSettingsLauncher
import heven.holt.library.longan.startActivity
import heven.holt.library.ui.base.BaseBindingActivity
import heven.holt.mvvm.databinding.ActivityLonganBinding
import java.io.File

class LonganActivity : BaseBindingActivity<ActivityLonganBinding>() {
    companion object {
        private const val TAG = "LonganActivity"

        fun startAtc() {
            startActivity<LonganActivity>()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = Uri.parse("content://downloads/all_downloads/4")
        Log.d(TAG, "startActivityLauncher:${File(uri.path).absolutePath}")

        immerseStatusBar()

    }

    private val startActivityLauncher = launchWifiSettingsLauncher {
        Log.d(TAG, "startActivityLauncher:$it")

    }

    fun finishActivityBtn(view: View) {
    }
}