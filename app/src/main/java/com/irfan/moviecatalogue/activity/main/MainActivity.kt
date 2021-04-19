package com.irfan.moviecatalogue.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.irfan.moviecatalogue.activity.main.adapter.FragmentAdapter
import com.irfan.moviecatalogue.databinding.ActivityMainBinding

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
        binding.tabLayout.apply {
            addTab(this.newTab().setText("Movie"))
            addTab(this.newTab().setText("Tv Show"))
        }

        val adapter = FragmentAdapter(supportFragmentManager, binding.tabLayout.tabCount)
        binding.viewPager.apply {
            this.adapter = adapter
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}