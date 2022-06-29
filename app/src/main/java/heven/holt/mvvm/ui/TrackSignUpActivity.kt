package heven.holt.mvvm.ui

import android.os.Bundle
import heven.holt.library.track.postTrack
import heven.holt.library.track.setPageTrackNode
import heven.holt.library.track.updateThreadTrackNode
import heven.holt.library.ui.base.BaseBindingActivity
import heven.holt.mvvm.databinding.ActivityTrackSignupBinding
import heven.holt.mvvm.track.ResultTrackNode

class TrackSignUpActivity : BaseBindingActivity<ActivityTrackSignupBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar("注册")
        setPageTrackNode()

        binding.btnSignUpSuccess.setOnClickListener { view ->
            view.updateThreadTrackNode(ResultTrackNode::class.java) { result = "success" }
            view.postTrack("click_sign_up", ResultTrackNode::class.java)
        }
        binding.btnSignUpFailure.setOnClickListener { view ->
            view.updateThreadTrackNode(ResultTrackNode::class.java) { result = "failure" }
            view.postTrack("click_sign_up", ResultTrackNode::class.java)
        }
    }
}