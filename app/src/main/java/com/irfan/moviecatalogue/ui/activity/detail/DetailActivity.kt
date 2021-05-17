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
import com.irfan.moviecatalogue.utils.IdlingResourceTarget
import com.irfan.moviecatalogue.utils.Status
import com.irfan.moviecatalogue.utils.Utils.hide
import com.irfan.moviecatalogue.utils.Utils.orIfBlank
import com.irfan.moviecatalogue.utils.Utils.rating
import com.irfan.moviecatalogue.utils.Utils.show
import com.irfan.moviecatalogue.utils.Utils.toClockTime
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel : DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding
    private var isFavorite: Boolean = false

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
        binding.btnFavorite.setOnClickListener {
            setFavoriteStatus()
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

        viewModel.setTypeOfItem(type)
        viewModel.getDetailItem(id)
        observeDetailItem()

        viewModel.checkIfItemIsFavorite(id).observe(this, {
            isFavorite = it
            setFavoriteIcon()
        })
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

    private fun observeDetailItem() {
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

                            tvTitle.text = inner.movieTitle orIfBlank inner.tvName
                            tvRelease.text = inner.releaseDate orIfBlank inner.firstAirDate
                            tvDuration.text = (inner.movieDuration?: (if (inner.tvDuration?.size == 0) null else inner.tvDuration?.get(0))).toClockTime(this@DetailActivity)
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

    private fun setFavoriteStatus() {
        isFavorite = !isFavorite
        if (isFavorite) {
            addToFavorite()
        } else {
            removeFromFavorite()
        }
        setFavoriteIcon()
    }

    private fun addToFavorite() {
        viewModel.addItemToFavourite()
    }

    private fun removeFromFavorite() {
        viewModel.removeItemFromFavourite()
    }

    private fun setFavoriteIcon() {
        if (isFavorite) {
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite_filled_color)
        } else {
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite_border_color)
        }
    }

}