package heven.holt.library.loadingstateview.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import heven.holt.library.R
import heven.holt.library.loadingstateview.LoadingStateView
import heven.holt.library.loadingstateview.ViewType

class ErrorViewDelegate : LoadingStateView.ViewDelegate(ViewType.ERROR) {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup): View =
        inflater.inflate(R.layout.layout_error, parent, false).apply {
            findViewById<View>(R.id.btn_reload).setOnClickListener {
                onReloadListener?.onReload()
            }
        }
}