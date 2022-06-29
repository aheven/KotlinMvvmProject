package heven.holt.mvvm.ui

import android.os.Bundle
import heven.holt.library.track.setPageTrackNode
import heven.holt.library.ui.base.BaseBindingActivity
import heven.holt.mvvm.adapter.TrackSeriesAdapter
import heven.holt.mvvm.databinding.LayoutTrackListBinding
import heven.holt.mvvm.model.TrackVideo
import heven.holt.mvvm.repository.DataRepository

class TrackSeriesActivity : BaseBindingActivity<LayoutTrackListBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPageTrackNode(getReferrerKeyMap()) { params -> params.put("page_name", "series") }

        val video: TrackVideo = intent.getSerializableExtra("video") as TrackVideo
        setToolbar(video.seriesName)

        val adapter = TrackSeriesAdapter()
        adapter.submitList(DataRepository.getSeriesMovies(video.id))
        binding.recyclerView.adapter = adapter
    }

    private fun getReferrerKeyMap(): Map<String, String> =
        mapOf("page_name" to "from_page", "tab_name" to "from_tab_name", "channel_name" to "from_channel_name")
}