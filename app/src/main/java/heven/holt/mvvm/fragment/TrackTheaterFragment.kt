package heven.holt.mvvm.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import heven.holt.library.track.TrackNode
import heven.holt.library.track.trackNode
import heven.holt.library.ui.base.BaseBindingFragment
import heven.holt.mvvm.databinding.FragmentTrackTheaterBinding

class TrackTheaterFragment : BaseBindingFragment<FragmentTrackTheaterBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trackNode = TrackNode("tab_name" to "theater")

        val fragments = listOf(TrackRecommendFragment(), TrackMovieFragment())
        binding.viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = fragments.size

            override fun createFragment(position: Int): Fragment = fragments[position]
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = when (position) {
                0 -> "推荐"
                1 -> "电影"
                else -> "推荐"
            }
        }.attach()
    }
}