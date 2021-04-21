package com.irfan.moviecatalogue.fragment.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.moviecatalogue.activity.detail.DetailActivity
import com.irfan.moviecatalogue.adapter.ListMovieAdapter
import com.irfan.moviecatalogue.databinding.FragmentMovieBinding
import com.irfan.moviecatalogue.model.Movie

class MovieFragment : Fragment() {

    private val viewModel : MovieViewModel by viewModels()
    private lateinit var binding : FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        viewModel.setData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovie.layoutManager = LinearLayoutManager(requireContext())
        showRecyclerView()
    }

    private fun showRecyclerView() {
        val listMovieAdapter = ListMovieAdapter(viewModel.getData()) {item->
            val movie = item as Movie
            showMovieDetail(movie)
        }
        binding.rvMovie.adapter = listMovieAdapter
    }

    private fun showMovieDetail(movie: Movie) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }
}