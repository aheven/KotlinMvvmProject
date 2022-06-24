package heven.holt.library.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import heven.holt.library.loadingstateview.Decorative
import heven.holt.library.loadingstateview.LoadingState
import heven.holt.library.loadingstateview.LoadingStateDelegate
import heven.holt.library.loadingstateview.OnReloadListener
import heven.holt.library.viewbinding.base.ActivityBinding
import heven.holt.library.viewbinding.base.ActivityBindingDelegate

abstract class BaseBindingActivity<VB : ViewBinding> : AppCompatActivity(),
    LoadingState by LoadingStateDelegate(), OnReloadListener, Decorative,
    ActivityBinding<VB> by ActivityBindingDelegate() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentViewWithBinding()
        binding.root.decorate(this, this)
    }

    override fun onReload() {
        super.onReload()
        showContentView()
    }
}