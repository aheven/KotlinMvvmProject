package heven.holt.library.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import heven.holt.library.loadingstateview.Decorative
import heven.holt.library.loadingstateview.LoadingState
import heven.holt.library.loadingstateview.LoadingStateDelegate
import heven.holt.library.loadingstateview.OnReloadListener

abstract class BaseFragment(private val layoutRes: Int) : Fragment(),
    LoadingState by LoadingStateDelegate(), OnReloadListener, Decorative {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(layoutRes, container, false)
        return root.decorate(this, this)
    }
}