package com.irfan.moviecatalogue.activity.detail


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.irfan.moviecatalogue.R
import com.irfan.moviecatalogue.databinding.ActivityDetailBinding
import com.irfan.moviecatalogue.model.Movie


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
        val movie = intent.getParcelableExtra(EXTRA_MOVIE)?:Movie()
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
            viewModel.getData().observe(this@DetailActivity, {
                Glide.with(this@DetailActivity)
                        .load(it.posterImg)
                        .into(imagePoster)

                Glide.with(this@DetailActivity)
                        .load(it.posterImg)
                        .into(imageBg)

                tvTitle.text = it.title
                tvRelease.text = it.release
                tvOverview.text = it.overview
                tvDuration.text = it.duration
                ratingBar.rating = it.score.rating()
            })
        }
    }

    private fun Int?.rating() : Float {
        if (this != null)
            return this.toFloat() / 20.0f

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
        viewModel.getData().observe(this, {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, """
                ${it.title}(${it.release})
                
                
                ${it.overview}
            """.trimIndent())
            }

            startActivity(Intent.createChooser(shareIntent, null))
        })
    }
}