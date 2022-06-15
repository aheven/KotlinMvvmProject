package heven.holt.library.viewbinding.ktx

import androidx.activity.ComponentActivity
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding

inline fun <reified VB : ViewBinding> ComponentActivity.binding(setContentView: Boolean = true) =
    lazy(LazyThreadSafetyMode.NONE) {
        inflateBinding<VB>(layoutInflater).also { binding ->
            if (setContentView) setContentView(binding.root)
            if (binding is ViewDataBinding) binding.lifecycleOwner = this
        }
    }