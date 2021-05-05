package com.irfan.moviecatalogue.ui.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.irfan.moviecatalogue.R
import com.irfan.moviecatalogue.databinding.ActivityMainBinding
import com.irfan.moviecatalogue.ui.activity.main.adapter.PagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setUpTabLayout()
    }

    private fun setUpTabLayout() {
        binding.apply {
            viewPager.adapter = PagerAdapter(this@MainActivity)

            TabLayoutMediator(tabLayout, viewPager) {tab, position ->
                when(position) {
                    0 -> tab.text = resources.getString(R.string.movie)
                    1 -> tab.text = resources.getString(R.string.tv_show)
                }
            }.attach()
        }
    }
}