package heven.holt.mvvm.track

import android.app.Application
import android.content.Context
import heven.holt.library.android_startup.AndroidStartup
import heven.holt.library.track.initTracker

class TrackInitializer : AndroidStartup<String>() {
    override fun create(context: Context): String? {
        initTracker(context.applicationContext as Application, AppTrackHandler())
        return TrackInitializer::class.java.simpleName
    }

    override fun callCreateOnMainThread(): Boolean = true

    override fun waitOnMainThread(): Boolean = false
}