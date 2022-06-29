package heven.holt.mvvm.track

import android.content.Context
import heven.holt.library.longan.logInfo
import heven.holt.library.track.TrackHandler

class AppTrackHandler : TrackHandler {
    override fun onEvent(context: Context, eventId: String, params: Map<String, String>) {
        logInfo("context=$context,eventId=$eventId,params=$params")
//        MobclickAgent.onEvent(context, eventId, params)
    }
}