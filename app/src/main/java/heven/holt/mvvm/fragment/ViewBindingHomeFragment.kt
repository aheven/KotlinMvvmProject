package heven.holt.mvvm.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import heven.holt.mvvm.R
import heven.holt.mvvm.databinding.FragmentViewBindingHomeBinding
import heven.holt.library.viewbinding.ktx.binding

class ViewBindingHomeFragment : Fragment(R.layout.fragment_view_binding_home) {
    private val binding: FragmentViewBindingHomeBinding by binding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddMessage.setOnClickListener {
            setFragmentResult("add_message", Bundle())
        }
    }
}