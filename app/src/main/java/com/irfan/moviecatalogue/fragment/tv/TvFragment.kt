package com.irfan.moviecatalogue.fragment.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.moviecatalogue.R
import com.irfan.moviecatalogue.adapter.ListMovieAdapter
import com.irfan.moviecatalogue.databinding.FragmentTvBinding
import com.irfan.moviecatalogue.model.Movie

class TvFragment : Fragment() {

    private val viewModel: TvViewModel by viewModels()
    private lateinit var binding : FragmentTvBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvBinding.inflate(inflater, container, false)
        viewModel.setData(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTv.layoutManager = LinearLayoutManager(requireContext())
        showRecyclerView()
    }

    private fun showRecyclerView() {
        viewModel.getData().observe(viewLifecycleOwner, {
            val listMovieAdapter = ListMovieAdapter(it) {

            }
            binding.rvTv.adapter = listMovieAdapter
        })
    }

    private fun moveToDetailActivity(movie: Movie) {

    }


}