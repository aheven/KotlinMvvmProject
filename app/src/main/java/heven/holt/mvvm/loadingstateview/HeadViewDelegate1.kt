package heven.holt.mvvm.loadingstateview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import heven.holt.library.R
import heven.holt.library.loadingstateview.LoadingStateView

class HeadViewDelegate1 : LoadingStateView.ViewDelegate("HeadViewDelegate1") {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup): View =
        inflater.inflate(R.layout.layout_head1, parent, false)
}