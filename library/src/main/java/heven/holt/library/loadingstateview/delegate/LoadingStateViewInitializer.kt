package heven.holt.library.loadingstateview.delegate

import android.content.Context
import heven.holt.library.android_startup.AndroidStartup
import heven.holt.library.loadingstateview.LoadingStateView

class LoadingStateViewInitializer : AndroidStartup<String>() {
    override fun create(context: Context): String? {
        LoadingStateView.setViewDelegatePool {
            register(ToolbarViewDelegate(), LoadingViewDelegate(), ErrorViewDelegate(), EmptyViewDelegate())
        }
        return LoadingStateViewInitializer::class.java.simpleName
    }

    override fun callCreateOnMainThread(): Boolean = true

    override fun waitOnMainThread(): Boolean = false
}