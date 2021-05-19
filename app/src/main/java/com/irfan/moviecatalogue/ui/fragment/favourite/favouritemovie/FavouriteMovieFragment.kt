package com.irfan.moviecatalogue.ui.fragment.favourite.favouritemovie


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.irfan.moviecatalogue.data.local.entity.Movie
import com.irfan.moviecatalogue.databinding.FragmentFavouriteBinding
import com.irfan.moviecatalogue.ui.activity.detail.DetailActivity
import com.irfan.moviecatalogue.ui.fragment.adapter.MoviePagedListAdapter
import com.irfan.moviecatalogue.utils.Utils.hide
import com.irfan.moviecatalogue.utils.Utils.show
import com.irfan.moviecatalogue.utils.Constants.MOVIE_TYPE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteMovieFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private val viewModel : FavouriteMovieViewModel by viewModels()
    private lateinit var binding : FragmentFavouriteBinding
    private lateinit var moviePagedListAdapter: MoviePagedListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeRefresh.setOnRefreshListener(this)
        setUpRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        observeMovieList()
    }

    private fun setUpRecyclerView() {
        moviePagedListAdapter = MoviePagedListAdapter { item ->
            val movie = item as Movie
            showMovieDetail(movie.id?:-1)
        }

        with(binding.rvFavourite) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = moviePagedListAdapter
        }
    }

    private fun observeMovieList() {
        viewModel.getFavouriteMovie().observe(viewLifecycleOwner, {
            moviePagedListAdapter.submitList(it)
            if (it.isNullOrEmpty()) {
                binding.noData.root.show()
            } else {
                binding.noData.root.hide()
            }
        })
    }

    private fun showMovieDetail(id: Int) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, id)
        intent.putExtra(DetailActivity.EXTRA_TYPE, MOVIE_TYPE)
        startActivity(intent)
    }

    override fun onRefresh() {
        viewModel.getFavouriteMovie()
        binding.swipeRefresh.isRefreshing = false
    }

}