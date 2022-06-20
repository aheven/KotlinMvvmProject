package heven.holt.mvvm.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import heven.holt.library.ui.base.BaseBindingActivity
import heven.holt.library.viewbinding.ktx.doOnCustomTabSelected
import heven.holt.library.viewbinding.ktx.setCustomView
import heven.holt.library.viewbinding.ktx.updateCustomTab
import heven.holt.mvvm.R
import heven.holt.mvvm.databinding.ActivityViewBindingKtxBinding
import heven.holt.mvvm.databinding.LayoutViewBindingBottomTabBinding
import heven.holt.mvvm.fragment.ViewBindingHomeFragment
import heven.holt.mvvm.fragment.ViewBindingMeFragment
import heven.holt.mvvm.fragment.ViewBindingMessageFragment

class ViewBindingKtxActivity : BaseBindingActivity<ActivityViewBindingKtxBinding>() {
    companion object {
        fun startAtc(context: Context) {
            val intent = Intent(context, ViewBindingKtxActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val tabs: List<CustomTab> by lazy {
        listOf(
            CustomTab(R.string.tab_home, R.drawable.ic_view_binding_home_selector, ViewBindingHomeFragment()),
            CustomTab(R.string.tab_message, R.drawable.ic_view_binding_message_selector, ViewBindingMessageFragment()),
            CustomTab(R.string.tab_me, R.drawable.ic_view_binding_account_selector, ViewBindingMeFragment())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = tabs.size
            override fun createFragment(position: Int): Fragment = tabs[position].fragment
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2, false) { tab, position ->
            tab.setCustomView<LayoutViewBindingBottomTabBinding> {
                tvTitle.text = getString(tabs[position].title)
                ivIcon.setImageResource(tabs[position].icon)
                tvTitle.contentDescription = getString(tabs[position].title)
            }
        }.attach()

        binding.tabLayout.doOnCustomTabSelected<LayoutViewBindingBottomTabBinding>(onTabSelected = { tab ->
            if (tab.position == 1) {
                ivUnreadState.isVisible = false
            }
        })

        supportFragmentManager.setFragmentResultListener("add_message", this) { _, _ ->
            binding.tabLayout.updateCustomTab<LayoutViewBindingBottomTabBinding>(1) {
                ivUnreadState.isVisible = true
            }
        }
    }

    data class CustomTab(
        val title: Int, val icon: Int, val fragment: Fragment
    )
}