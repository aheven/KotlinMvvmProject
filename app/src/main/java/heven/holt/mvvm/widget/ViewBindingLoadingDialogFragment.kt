package heven.holt.mvvm.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import heven.holt.mvvm.R
import heven.holt.mvvm.databinding.DialogViewBindingLoadingBinding
import heven.holt.library.viewbinding.ktx.binding

class ViewBindingLoadingDialogFragment(
    private val text: String? = null
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return LoadingDialog(requireContext())
    }

    inner class LoadingDialog(context: Context) : Dialog(context, R.style.DialogTheme) {
        private val binding: DialogViewBindingLoadingBinding by binding()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setCancelable(false)
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            with(binding) {
                if (text.isNullOrBlank()) {
                    tvMsg.isVisible = false
                } else {
                    tvMsg.isVisible = true
                    tvMsg.text = text
                }
            }
        }
    }
}


