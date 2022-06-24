package heven.holt.library.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import heven.holt.library.loadingstateview.Decorative
import heven.holt.library.loadingstateview.LoadingState
import heven.holt.library.loadingstateview.LoadingStateDelegate
import heven.holt.library.loadingstateview.OnReloadListener

abstract class BaseActivity(private val layoutRes: Int) : AppCompatActivity(),
    LoadingState by LoadingStateDelegate(), OnReloadListener, Decorative {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        decorateContentView(this, this)
    }
}