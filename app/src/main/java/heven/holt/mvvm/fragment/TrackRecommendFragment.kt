package heven.holt.mvvm.fragment

import android.os.Bundle
import android.view.View
import heven.holt.library.track.TrackNode
import heven.holt.library.track.trackNode
import heven.holt.library.ui.base.BaseBindingFragment
import heven.holt.mvvm.adapter.TrackVideoAdapter
import heven.holt.mvvm.databinding.FragmentTrackRecommendBinding
import heven.holt.mvvm.repository.DataRepository

class TrackRecommendFragment : BaseBindingFragment<FragmentTrackRecommendBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trackNode = TrackNode("channel_name" to "recommend")

        val videoAdapter = TrackVideoAdapter()
        binding.recyclerView.adapter = videoAdapter
        videoAdapter.submitList(DataRepository.getRecommendVideos())
    }
}