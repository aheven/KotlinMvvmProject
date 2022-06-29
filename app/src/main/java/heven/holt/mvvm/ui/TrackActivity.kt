package heven.holt.mvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import heven.holt.library.longan.startActivity
import heven.holt.library.track.TrackNode
import heven.holt.library.track.trackNode
import heven.holt.library.ui.base.BaseBindingActivity
import heven.holt.mvvm.R
import heven.holt.mvvm.databinding.ActivityTrackBinding
import heven.holt.mvvm.fragment.TrackHomeFragment
import heven.holt.mvvm.fragment.TrackMineFragment
import heven.holt.mvvm.fragment.TrackTheaterFragment

class TrackActivity : BaseBindingActivity<ActivityTrackBinding>() {
    companion object {
        fun startAtc() {
            startActivity<TrackActivity>()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trackNode = TrackNode("page_name" to "main")
        setToolbar("Track")

        val fragments = listOf(TrackHomeFragment(), TrackTheaterFragment(), TrackMineFragment())
        binding.viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = fragments.size

            override fun createFragment(position: Int): Fragment = fragments[position]
        }
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            binding.viewPager2.currentItem = when (item.itemId) {
                R.id.page_1 -> 0
                R.id.page_2 -> 1
                R.id.page_3 -> 2
                else -> 0
            }
            true
        }
    }
}