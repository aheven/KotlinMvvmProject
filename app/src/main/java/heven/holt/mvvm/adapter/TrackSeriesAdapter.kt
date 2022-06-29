package heven.holt.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import heven.holt.library.longan.intentOf
import heven.holt.library.longan.startActivity
import heven.holt.library.track.TrackNode
import heven.holt.library.track.postTrack
import heven.holt.library.track.putReferrerTrackNode
import heven.holt.library.track.trackNode
import heven.holt.mvvm.databinding.ItemTrackSeriesBinding
import heven.holt.mvvm.model.TrackVideo
import heven.holt.mvvm.ui.TrackDetailsActivity

class TrackSeriesAdapter : ListAdapter<TrackVideo, TrackSeriesAdapter.ViewHolder>(TrackVideo.Companion.DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemTrackSeriesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = getItem(position)
        holder.itemView.trackNode = TrackNode("video_id" to video.id, "video_type" to video.type)
        holder.binding.tvTitle.text = video.title
    }

    inner class ViewHolder(val binding: ItemTrackSeriesBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnFavorite.setOnClickListener { view -> view.postTrack("click_favorite") }
            itemView.setOnClickListener { view ->
                val intent = view.context.intentOf<TrackDetailsActivity>()
                    .putExtra("video", getItem(adapterPosition))
                    .putReferrerTrackNode(view)
                startActivity(intent)
            }
        }
    }
}