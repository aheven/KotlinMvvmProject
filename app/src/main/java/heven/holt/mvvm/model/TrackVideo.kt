package heven.holt.mvvm.model

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class TrackVideo(
    val id: String,
    val title: String,
    val type: String,
    val seriesName: String?
) : Serializable {
    companion object {
        class DiffCallback : DiffUtil.ItemCallback<TrackVideo>() {
            override fun areItemsTheSame(oldItem: TrackVideo, newItem: TrackVideo): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TrackVideo, newItem: TrackVideo): Boolean =
                oldItem.title == newItem.title
        }
    }

    constructor(id: String, title: String, type: String) : this(id, title, type, null)
}