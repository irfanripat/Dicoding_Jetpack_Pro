package com.irfan.moviecatalogue.fragment.tv

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
import com.irfan.moviecatalogue.databinding.FragmentTvBinding
import com.irfan.moviecatalogue.model.Movie

class TvFragment : Fragment() {

    private val viewModel: TvViewModel by viewModels()
    private lateinit var binding : FragmentTvBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvBinding.inflate(inflater, container, false)
        viewModel.setData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTv.layoutManager = LinearLayoutManager(requireContext())
        showRecyclerView()
    }

    private fun showRecyclerView() {
        val listMovieAdapter = ListMovieAdapter(viewModel.getData()) {item->
            val tv = item as Movie
            showTvDetail(tv)
        }
        binding.rvTv.adapter = listMovieAdapter
    }

    private fun showTvDetail(tv: Movie) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE, tv)
        startActivity(intent)
    }

}