package heven.holt.mvvm.fragment

import android.os.Bundle
import android.view.View
import heven.holt.library.ui.base.BaseBindingFragment
import heven.holt.mvvm.databinding.FragmentViewBindingCustomViewBinding
import heven.holt.mvvm.widget.ViewBindingLoadingDialogFragment

class ViewBindingMeFragment : BaseBindingFragment<FragmentViewBindingCustomViewBinding>() {
    private val loadingDialog by lazy { ViewBindingLoadingDialogFragment() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customView.setOnClickListener {
            loadingDialog.show(childFragmentManager, "loading")
            it.postDelayed({ loadingDialog.dismiss() }, 2000)
        }
    }
}