package com.irfan.moviecatalogue.ui.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.irfan.moviecatalogue.R
import com.irfan.moviecatalogue.databinding.ActivityMainBinding
import com.irfan.moviecatalogue.ui.activity.favourite.FavouriteActivity
import com.irfan.moviecatalogue.ui.activity.main.adapter.PagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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
        supportActionBar?.title = resources.getString(R.string.app_name)
        supportActionBar?.setDisplayShowTitleEnabled(true)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_favourite) {
            startActivity(Intent(this@MainActivity, FavouriteActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}