package com.irfan.moviecatalogue.ui.fragment.favourite.favouritetv


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.irfan.moviecatalogue.data.local.entity.TvShow
import com.irfan.moviecatalogue.databinding.FragmentFavouriteBinding
import com.irfan.moviecatalogue.ui.activity.detail.DetailActivity
import com.irfan.moviecatalogue.ui.fragment.adapter.TvPagedListAdapter
import com.irfan.moviecatalogue.utils.Utils.hide
import com.irfan.moviecatalogue.utils.Utils.show
import com.irfan.moviecatalogue.utils.Constants.TV_TYPE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteTvFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private val viewModel : FavouriteTvViewModel by viewModels()
    private lateinit var binding : FragmentFavouriteBinding
    private lateinit var tvPagedListAdapter: TvPagedListAdapter

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
        tvPagedListAdapter = TvPagedListAdapter { item ->
            val tv = item as TvShow
            showMovieDetail(tv.id?:-1)
        }

        with(binding.rvFavourite) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = tvPagedListAdapter
        }
    }

    private fun observeMovieList() {
        viewModel.getFavouriteTv().observe(viewLifecycleOwner, {
            tvPagedListAdapter.submitList(it)
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
        intent.putExtra(DetailActivity.EXTRA_TYPE, TV_TYPE)
        startActivity(intent)
    }

    override fun onRefresh() {
        viewModel.getFavouriteTv()
        binding.swipeRefresh.isRefreshing = false
    }

}