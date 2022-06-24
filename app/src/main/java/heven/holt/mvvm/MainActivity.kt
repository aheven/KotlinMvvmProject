package heven.holt.mvvm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import heven.holt.library.loadingstateview.NavBtnType
import heven.holt.library.ui.base.BaseBindingActivity
import heven.holt.mvvm.loadingstateview.HeadViewDelegate
import heven.holt.mvvm.loadingstateview.HeadViewDelegate1
import heven.holt.mvvm.databinding.ActivityMainBinding
import heven.holt.mvvm.ui.LonganActivity
import heven.holt.mvvm.ui.StartupActivity
import heven.holt.mvvm.ui.ViewBindingKtxActivity

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHeaders(ToolbarViewDelegate("标题栏", NavBtnType.NONE) {
            rightText("Delete") {
                Toast.makeText(this@MainActivity, "Delete", Toast.LENGTH_SHORT).show()
            }
        }, HeadViewDelegate(), HeadViewDelegate1())
    }

    fun intentViewBindingKtx(view: View) {
        ViewBindingKtxActivity.startAtc()
    }

    fun intentStartupManager(view: View) {
        StartupActivity.startAtc()
    }

    fun intentLongan(view: View) {
        LonganActivity.startAtc()
    }
}