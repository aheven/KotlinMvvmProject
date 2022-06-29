package heven.holt.mvvm.ui

import android.os.Bundle
import heven.holt.library.track.postTrack
import heven.holt.library.track.setPageTrackNode
import heven.holt.library.ui.base.BaseBindingActivity
import heven.holt.mvvm.databinding.ActivityTrackDetailsBinding
import heven.holt.mvvm.model.TrackVideo

class TrackDetailsActivity : BaseBindingActivity<ActivityTrackDetailsBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar("详情")
        setPageTrackNode(getReferrerKeyMap()) { params -> params.put("page_name", "details") }

        val video = intent.getSerializableExtra("video") as TrackVideo
        binding.tvTitle.text = video.title
        binding.btnFavorite.setOnClickListener { view -> view.postTrack("click_favorite") }
    }

    private fun getReferrerKeyMap(): Map<String, String> =
        mapOf("page_name" to "from_page", "tab_name" to "from_tab_name", "channel_name" to "from_channel_name")
}