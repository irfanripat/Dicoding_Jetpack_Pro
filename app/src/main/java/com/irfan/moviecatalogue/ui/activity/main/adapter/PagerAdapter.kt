package com.irfan.moviecatalogue.ui.activity.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.irfan.moviecatalogue.ui.fragment.movie.MovieFragment
import com.irfan.moviecatalogue.ui.fragment.tv.TvFragment

class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragment()
            1 -> TvFragment()
            else -> createFragment(position)
        }
    }

    override fun getItemCount(): Int = 2
}