package heven.holt.mvvm.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import heven.holt.library.track.TrackNode
import heven.holt.library.track.trackNode
import heven.holt.library.ui.base.BaseBindingFragment
import heven.holt.mvvm.adapter.TrackVideoAdapter
import heven.holt.mvvm.databinding.LayoutTrackListBinding
import heven.holt.mvvm.repository.DataRepository

class TrackMovieFragment : BaseBindingFragment<LayoutTrackListBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trackNode = TrackNode("channel_name" to "movie")
        val adapter = TrackVideoAdapter()
        adapter.submitList(DataRepository.getMovieVideos())
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
    }
}