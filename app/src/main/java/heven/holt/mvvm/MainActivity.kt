package heven.holt.mvvm

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import heven.holt.mvvm.ui.ViewBindingKtxActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    fun intentViewBindingKtx(view: View) {
        ViewBindingKtxActivity.startAtc(view.context)
    }
}