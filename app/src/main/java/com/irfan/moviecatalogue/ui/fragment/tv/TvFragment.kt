package com.irfan.moviecatalogue.ui.fragment.tv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.irfan.moviecatalogue.ui.activity.detail.DetailActivity
import com.irfan.moviecatalogue.ui.fragment.adapter.ListMovieAdapter
import com.irfan.moviecatalogue.databinding.FragmentTvBinding
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import com.irfan.moviecatalogue.utils.Commons.hide
import com.irfan.moviecatalogue.utils.Commons.show
import com.irfan.moviecatalogue.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: TvViewModel by activityViewModels()
    private lateinit var binding : FragmentTvBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTv.layoutManager = LinearLayoutManager(requireContext())
        observeTvList()
        binding.swipeRefresh.setOnRefreshListener(this)
    }

    private fun observeTvList() {
        viewModel.listTv.observe(viewLifecycleOwner, {
            if (!it.data?.results.isNullOrEmpty()) {
                val listMovieAdapter = ListMovieAdapter(it.data!!.results) { item ->
                    val tv = item as MovieResponse
                    showTvDetail(tv)
                }
                binding.rvTv.adapter = listMovieAdapter
            }

            when (it.status) {
                Status.LOADING -> {
                    showLoadingIndicator()
                    binding.apply {
                        rvTv.hide()
                        noConnection.root.hide()
                    }
                }
                Status.ERROR -> {
                    hideLoadingIndicator()
                    binding.apply {
                        rvTv.hide()
                        noConnection.root.show()
                    }
                }
                Status.SUCCESS -> {
                    hideLoadingIndicator()
                    binding.apply {
                        rvTv.show()
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

    private fun showTvDetail(tv: MovieResponse) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE, tv)
        startActivity(intent)
    }

    override fun onRefresh() {
        viewModel.getPopularTv()
        binding.swipeRefresh.isRefreshing = false
    }

}