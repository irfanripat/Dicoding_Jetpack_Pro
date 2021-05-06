package com.irfan.moviecatalogue.ui.activity.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.irfan.moviecatalogue.R
import com.irfan.moviecatalogue.databinding.ActivityDetailBinding
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import com.irfan.moviecatalogue.utils.Constants.IMAGE_URL


class DetailActivity : AppCompatActivity() {

    private val viewModel : DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_MOVIE = "movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setData()
        setContentView(binding.root)
        setupActionBar()
        getData()
    }

    private fun setData() {
        val movie = intent.getParcelableExtra(EXTRA_MOVIE)?: MovieResponse()
        viewModel.setData(movie)
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbar)
        binding.appBar.bringToFront()
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun getData() {
        binding.apply {
            viewModel.detailMovie.observe(this@DetailActivity, {
                    Glide.with(this@DetailActivity).apply {
                        load(IMAGE_URL + it.poster_path).into(imagePoster)
                        load(IMAGE_URL + it.backdrop_path).into(imageBg)
                    }
                    tvTitle.text = if (it.original_name.isNullOrBlank()) it.original_title else it.original_name
                    tvRelease.text = if (it.release_date.isNullOrBlank()) it.first_air_date else it.release_date
                    tvOverview.text = it.overview
//                    tvDuration.text = "Duration"
                    ratingBar.rating = it.vote_average.rating()
                }
            )
        }
    }

    private fun Double?.rating() : Float {
        if (this != null)
            return this.toFloat() / 2.0f

        return 0.0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_share) {
            shareData()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun shareData() {
        viewModel.detailMovie.observe(this@DetailActivity, {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, """
                ${if (it.original_name.isNullOrBlank()) it.original_title else it.original_name}(${if (it.first_air_date.isNullOrBlank()) it.release_date else it.first_air_date})
                
                
                ${it.overview}
                """.trimIndent())
            }
            startActivity(Intent.createChooser(shareIntent, null))
        })
    }
}