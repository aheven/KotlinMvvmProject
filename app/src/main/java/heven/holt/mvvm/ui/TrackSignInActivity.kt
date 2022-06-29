package heven.holt.mvvm.ui

import android.os.Bundle
import heven.holt.library.longan.intentOf
import heven.holt.library.longan.startActivity
import heven.holt.library.track.postTrack
import heven.holt.library.track.putReferrerTrackNode
import heven.holt.library.track.putThreadTrackNode
import heven.holt.library.track.updateThreadTrackNode
import heven.holt.library.ui.base.BaseBindingActivity
import heven.holt.mvvm.databinding.ActivityTrackSigninBinding
import heven.holt.mvvm.track.ResultTrackNode

class TrackSignInActivity : BaseBindingActivity<ActivityTrackSigninBinding>() {
    companion object {
        fun startAtc() {
            startActivity<TrackSignInActivity>()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar("登录")
        putThreadTrackNode(ResultTrackNode())

        binding.btnSignInSuccess.setOnClickListener { view ->
            view.updateThreadTrackNode(ResultTrackNode::class.java) { result = "success" }
            view.postTrack("click_sign_in", ResultTrackNode::class.java)
        }
        binding.btnSignInFailure.setOnClickListener { view ->
            view.updateThreadTrackNode(ResultTrackNode::class.java) { result = "failure" }
            view.postTrack("click_sign_in", ResultTrackNode::class.java)
        }
        binding.btnSignUp.setOnClickListener { view ->
            val intent = view.context.intentOf<TrackSignUpActivity>()
                .putReferrerTrackNode(view)
            startActivity(intent)
        }
    }
}