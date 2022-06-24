package heven.holt.library.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import heven.holt.library.loadingstateview.Decorative
import heven.holt.library.loadingstateview.LoadingState
import heven.holt.library.loadingstateview.LoadingStateDelegate
import heven.holt.library.loadingstateview.OnReloadListener
import heven.holt.library.viewbinding.base.FragmentBinding
import heven.holt.library.viewbinding.base.FragmentBindingDelegate

abstract class BaseBindingFragment<VB : ViewBinding> : Fragment(),
    LoadingState by LoadingStateDelegate(), OnReloadListener, Decorative,
    FragmentBinding<VB> by FragmentBindingDelegate() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return createViewWithBinding(inflater, container).decorate(this, this)
    }
}