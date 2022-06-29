package heven.holt.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import heven.holt.library.longan.intentOf
import heven.holt.library.longan.startActivity
import heven.holt.library.track.TrackNode
import heven.holt.library.track.postTrack
import heven.holt.library.track.putReferrerTrackNode
import heven.holt.library.track.trackNode
import heven.holt.mvvm.databinding.ItemTrackVideoBinding
import heven.holt.mvvm.model.TrackVideo
import heven.holt.mvvm.ui.TrackDetailsActivity
import heven.holt.mvvm.ui.TrackSeriesActivity

class TrackVideoAdapter : ListAdapter<TrackVideo, TrackVideoAdapter.ViewHolder>(TrackVideo.Companion.DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemTrackVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val video = getItem(position)
        holder.itemView.trackNode =
            TrackNode("video_id" to video.id, "video_type" to video.type)
        binding.tvTitle.text = video.title
        binding.tvSeriesName.isVisible = !video.seriesName.isNullOrEmpty()
        binding.tvSeriesName.text = video.seriesName
    }

    inner class ViewHolder(val binding: ItemTrackVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnFavorite.setOnClickListener { view -> view.postTrack("click_favorite") }
            binding.tvSeriesName.setOnClickListener { view ->
                val intent = view.context.intentOf<TrackSeriesActivity>()
                    .putExtra("video", getItem(adapterPosition))
                    .putReferrerTrackNode(view)
                startActivity(intent)
            }
            itemView.setOnClickListener { view ->
                val intent = view.context.intentOf<TrackDetailsActivity>()
                    .putExtra("video", getItem(adapterPosition))
                    .putReferrerTrackNode(view)
                startActivity(intent)
            }
        }
    }
}