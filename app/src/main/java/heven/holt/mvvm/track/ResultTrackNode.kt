package heven.holt.mvvm.track

import heven.holt.library.track.TrackNode
import heven.holt.library.track.TrackParams

class ResultTrackNode : TrackNode {
    var result: String? = null

    override fun fillTackParams(params: TrackParams) {
        if (!result.isNullOrEmpty()) {
            params.put("result", result)
        }
    }
}