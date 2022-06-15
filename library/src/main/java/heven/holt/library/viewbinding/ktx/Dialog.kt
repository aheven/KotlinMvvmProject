package heven.holt.library.viewbinding.ktx

import android.app.Dialog
import androidx.viewbinding.ViewBinding

inline fun <reified VB : ViewBinding> Dialog.binding() = lazy(LazyThreadSafetyMode.NONE) {
    inflateBinding<VB>(layoutInflater).also { setContentView(it.root) }
}