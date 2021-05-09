package com.irfan.moviecatalogue.ui.activity.detail


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.irfan.moviecatalogue.R
import com.irfan.moviecatalogue.databinding.ActivityDetailBinding
import com.irfan.moviecatalogue.utils.Constants.IMAGE_URL
import com.irfan.moviecatalogue.utils.Constants.MOVIE_TYPE
import com.irfan.moviecatalogue.utils.Constants.TV_TYPE
import com.irfan.moviecatalogue.utils.IdlingResourceTarget
import com.irfan.moviecatalogue.utils.Status
import com.irfan.moviecatalogue.utils.Utils.hide
import com.irfan.moviecatalogue.utils.Utils.rating
import com.irfan.moviecatalogue.utils.Utils.show
import com.irfan.moviecatalogue.utils.Utils.toClockTime
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel : DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_ID = "id"
        const val EXTRA_TYPE = "type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBinding()
        setData()
        setupActionBar()
        binding.noConnection.btnRefresh.setOnClickListener {
            setData()
        }
    }

    private fun setUpBinding() {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.noConnection.tvNoConnection.setTextColor(ContextCompat.getColor(this, R.color.white))
    }

    private fun setData() {
        val id = intent.getIntExtra(EXTRA_ID, -1)
        val type = intent.getStringExtra(EXTRA_TYPE)

        when (intent.getStringExtra(EXTRA_TYPE)) {
            MOVIE_TYPE -> viewModel.getDetailMovie(id)
            TV_TYPE -> viewModel.getDetailTv(id)
        }

        observeDetailItem(type)
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

    private fun observeDetailItem(type : String?) {
        binding.apply {
            viewModel.detailItem.observe(this@DetailActivity, {
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.noConnection.root.hide()
                        binding.ratingBar.show()
                        binding.labelOverview.show()
                        hideLoadingIndicator()
                        it.data?.let { inner ->
                            Glide.with(this@DetailActivity).apply {
                                load(IMAGE_URL + inner.posterPath)
                                        .error(R.drawable.default_placeholder)
                                        .into(IdlingResourceTarget(imagePoster))

                                load(IMAGE_URL + inner.backdropPath)
                                        .error(R.drawable.default_placeholder)
                                        .into(IdlingResourceTarget(imageBg))
                            }
                            tvTitle.text = if (type == MOVIE_TYPE) inner.movieTitle else inner.tvName
                            tvRelease.text = if (type == MOVIE_TYPE) inner.releaseDate else inner.firstAirDate
                            tvDuration.text = if (type == MOVIE_TYPE) inner.movieDuration.toClockTime(this@DetailActivity) else (if (inner.tvDuration!!.isEmpty()) null.toClockTime(this@DetailActivity) else inner.tvDuration!![0].toClockTime(this@DetailActivity))
                            tvOverview.text = inner.overview
                            ratingBar.rating = inner.score.rating()
                        }
                    }
                    Status.LOADING -> {
                        binding.noConnection.root.hide()
                        showLoadingIndicator()
                    }
                    Status.ERROR -> {
                        binding.noConnection.root.show()
                        hideLoadingIndicator()
                        binding.ratingBar.hide()
                        binding.labelOverview.hide()
                    }
                }
            })
        }
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
        viewModel.detailItem.observe(this@DetailActivity, {
            it.data?.let {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, """
                ${if (it.tvName.isNullOrBlank()) it.movieTitle else it.tvName}(${if (it.firstAirDate.isNullOrBlank()) it.releaseDate else it.firstAirDate})


                ${it.overview}
                """.trimIndent())
                }
                startActivity(Intent.createChooser(shareIntent, null))
            }
        })
    }

    private fun showLoadingIndicator() {
        binding.shimmer.apply {
            startShimmer()
            show()
        }
        binding.ratingBar.hide()
    }

    private fun hideLoadingIndicator() {
        binding.shimmer.apply {
            hide()
            stopShimmer()
        }
        binding.ratingBar.show()
    }
}