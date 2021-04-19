package com.irfan.moviecatalogue.fragment.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.moviecatalogue.adapter.ListMovieAdapter
import com.irfan.moviecatalogue.databinding.FragmentMovieBinding
import com.irfan.moviecatalogue.model.Movie

class MovieFragment : Fragment() {

    private val viewModel : MovieViewModel by viewModels()
    private lateinit var binding : FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        viewModel.setData(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovie.layoutManager = LinearLayoutManager(requireContext())
        showRecyclerView()
    }

    private fun showRecyclerView() {
        viewModel.getData().observe(viewLifecycleOwner, {
            val listMovieAdapter = ListMovieAdapter(it) {

            }
            binding.rvMovie.adapter = listMovieAdapter
        })
    }

    private fun moveToDetailActivity(movie: Movie) {

    }
}