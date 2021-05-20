package com.irfan.moviecatalogue.ui.activity.favourite.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.irfan.moviecatalogue.ui.fragment.favourite.favouritemovie.FavouriteMovieFragment
import com.irfan.moviecatalogue.ui.fragment.favourite.favouritetv.FavouriteTvFragment

class FavouritePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavouriteMovieFragment()
            1 -> FavouriteTvFragment()
            else -> createFragment(position)
        }
    }

    override fun getItemCount(): Int = 2
}