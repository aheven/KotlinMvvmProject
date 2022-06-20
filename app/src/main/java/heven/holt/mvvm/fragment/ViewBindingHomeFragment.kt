package heven.holt.mvvm.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import heven.holt.library.ui.base.BaseBindingFragment
import heven.holt.mvvm.databinding.FragmentViewBindingHomeBinding

class ViewBindingHomeFragment : BaseBindingFragment<FragmentViewBindingHomeBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddMessage.setOnClickListener {
            setFragmentResult("add_message", Bundle())
        }
    }
}