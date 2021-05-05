package com.irfan.moviecatalogue.ui.fragment.movie


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.irfan.moviecatalogue.ui.activity.detail.DetailActivity
import com.irfan.moviecatalogue.ui.fragment.adapter.ListMovieAdapter
import com.irfan.moviecatalogue.databinding.FragmentMovieBinding
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import com.irfan.moviecatalogue.utils.Commons.hide
import com.irfan.moviecatalogue.utils.Commons.show
import com.irfan.moviecatalogue.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private val viewModel : MovieViewModel by viewModels()
    private lateinit var binding : FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovie.layoutManager = LinearLayoutManager(requireContext())
        observeMovieList()
        binding.swipeRefresh.setOnRefreshListener(this)
    }

    private fun observeMovieList() {
        viewModel.listMovie.observe(viewLifecycleOwner, {
            if (!it.data?.results.isNullOrEmpty()) {
                val listMovieAdapter = ListMovieAdapter(it.data!!.results) { item ->
                    val movie = item as MovieResponse
                    showMovieDetail(movie)
                }
                binding.rvMovie.adapter = listMovieAdapter
            }

            when (it.status) {
                Status.LOADING -> {
                    showLoadingIndicator()
                    binding.apply {
                        rvMovie.hide()
                        noConnection.root.hide()
                    }
                }
                Status.ERROR -> {
                    hideLoadingIndicator()
                    binding.apply {
                        rvMovie.hide()
                        noConnection.root.show()
                    }
                }
                Status.SUCCESS -> {
                    hideLoadingIndicator()
                    binding.apply {
                        rvMovie.show()
                        noConnection.root.hide()
                    }
                }
            }
        })
    }

    private fun showLoadingIndicator() {
        binding.shimmer.apply {
            startShimmer()
            show()
        }
    }

    private fun hideLoadingIndicator() {
        binding.shimmer.apply {
            hide()
            stopShimmer()
        }
    }

    private fun showMovieDetail(movie: MovieResponse) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }

    override fun onRefresh() {
        viewModel.getPopularMovie()
        binding.swipeRefresh.isRefreshing = false
    }

}