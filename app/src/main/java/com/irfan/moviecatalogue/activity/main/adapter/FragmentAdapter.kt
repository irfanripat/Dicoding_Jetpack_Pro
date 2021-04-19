package com.irfan.moviecatalogue.activity.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.irfan.moviecatalogue.fragment.movie.MovieFragment
import com.irfan.moviecatalogue.fragment.tv.TvFragment

internal class FragmentAdapter(fm: FragmentManager, private var totalTabs: Int) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragment()
            1 -> TvFragment()
            else -> getItem(position)
        }
    }

    override fun getCount(): Int = totalTabs
}