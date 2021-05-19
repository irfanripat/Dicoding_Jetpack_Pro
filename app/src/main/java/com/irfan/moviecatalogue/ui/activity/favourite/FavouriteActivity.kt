package com.irfan.moviecatalogue.ui.activity.favourite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.irfan.moviecatalogue.R
import com.irfan.moviecatalogue.databinding.ActivityMainBinding
import com.irfan.moviecatalogue.ui.activity.favourite.adapter.FavouritePagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpActionBar()
        setUpTabLayout()
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Favourite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun setUpTabLayout() {
        binding.apply {
            viewPager.adapter = FavouritePagerAdapter(this@FavouriteActivity)

            TabLayoutMediator(tabLayout, viewPager) {tab, position ->
                when(position) {
                    0 -> tab.text = resources.getString(R.string.movie)
                    1 -> tab.text = resources.getString(R.string.tv_show)
                }
            }.attach()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}