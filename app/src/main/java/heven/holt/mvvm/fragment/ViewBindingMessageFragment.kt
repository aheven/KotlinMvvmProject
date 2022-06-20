package heven.holt.mvvm.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import heven.holt.library.ui.base.BaseBindingFragment
import heven.holt.library.viewbinding.base.simpleStringListAdapter
import heven.holt.mvvm.databinding.FragmentViewBindingMessageBinding
import heven.holt.mvvm.databinding.ItemViewBindingFooBinding

class ViewBindingMessageFragment : BaseBindingFragment<FragmentViewBindingMessageBinding>() {

    private val list = listOf("item 1", "item 2", "item 3")

    private val adapter by simpleStringListAdapter<ItemViewBindingFooBinding> {
        textView.text = it
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        adapter.submitList(list)

        adapter.doOnItemClick { item, _ ->
            Toast.makeText(requireContext(), item, Toast.LENGTH_SHORT).show()
        }

        adapter.doOnItemLongClick { item, _ ->
            Toast.makeText(requireContext(), "long click $item", Toast.LENGTH_SHORT).show()
        }
    }
}