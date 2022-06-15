package heven.holt.library.viewbinding.ktx

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline fun <reified VB : ViewBinding> ViewGroup.inflate() =
    inflateBinding<VB>(LayoutInflater.from(context), this, true)

inline fun <reified VB : ViewBinding> ViewGroup.binding(attachToParent: Boolean = false) =
    lazy(LazyThreadSafetyMode.NONE) {
        inflateBinding<VB>(LayoutInflater.from(context), if (attachToParent) this else null, attachToParent)
    }