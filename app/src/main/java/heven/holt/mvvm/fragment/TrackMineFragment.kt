package heven.holt.mvvm.fragment

import android.os.Bundle
import android.view.View
import heven.holt.library.ui.base.BaseFragment
import heven.holt.mvvm.R
import heven.holt.mvvm.ui.TrackSignInActivity

class TrackMineFragment : BaseFragment(R.layout.fragment_track_mine) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.btn_sign_in).setOnClickListener {
            TrackSignInActivity.startAtc()
        }
    }
}