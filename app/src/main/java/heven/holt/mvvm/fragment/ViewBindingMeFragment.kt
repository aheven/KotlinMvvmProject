package heven.holt.mvvm.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import heven.holt.mvvm.R
import heven.holt.mvvm.databinding.FragmentViewBindingCustomViewBinding
import heven.holt.library.viewbinding.ktx.binding
import heven.holt.mvvm.widget.ViewBindingLoadingDialogFragment

class ViewBindingMeFragment : Fragment(R.layout.fragment_view_binding_custom_view) {
    private val binding by binding<FragmentViewBindingCustomViewBinding>()
    private val loadingDialog by lazy { ViewBindingLoadingDialogFragment() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customView.setOnClickListener {
            loadingDialog.show(childFragmentManager, "loading")
            it.postDelayed({ loadingDialog.dismiss() }, 2000)
        }
    }
}