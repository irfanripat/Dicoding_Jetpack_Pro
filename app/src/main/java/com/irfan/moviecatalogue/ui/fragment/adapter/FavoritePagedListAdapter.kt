package com.irfan.moviecatalogue.ui.fragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irfan.moviecatalogue.R
import com.irfan.moviecatalogue.data.local.entity.Movie
import com.irfan.moviecatalogue.data.local.entity.TvShow
import com.irfan.moviecatalogue.databinding.ItemMovieBinding
import com.irfan.moviecatalogue.utils.Constants
import com.irfan.moviecatalogue.utils.IdlingResourceTarget

class MoviePagedListAdapter(private val listener: (Any) -> Unit) : PagedListAdapter<Movie, MoviePagedListAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position) as Movie, listener)
    }

    inner class MovieViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemMovieBinding.bind(view)
        fun bind(listItem: Movie, listener: (Any) -> Unit) {
            binding.apply {
                tvTitle.text = listItem.movieTitle
                tvRelease.text = listItem.releaseDate
                tvOverview.text = listItem.overview
                Glide.with(view.context)
                    .load(Constants.IMAGE_URL + listItem.posterPath)
                    .error(R.drawable.default_placeholder)
                    .into(
                        IdlingResourceTarget(imagePoster)
                    )
            }

            itemView.setOnClickListener { listener(listItem) }
        }
    }
}

class TvPagedListAdapter(private val listener: (Any) -> Unit) : PagedListAdapter<TvShow, TvPagedListAdapter.TvViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<TvShow> = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem.id == newItem.id
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        return TvViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: TvPagedListAdapter.TvViewHolder, position: Int) {
        holder.bind(getItem(position) as TvShow, listener)
    }

    inner class TvViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemMovieBinding.bind(view)
        fun bind(listItem: TvShow, listener: (Any) -> Unit) {
            binding.apply {
                tvTitle.text = listItem.tvName
                tvRelease.text = listItem.firstAirDate
                tvOverview.text = listItem.overview
                Glide.with(view.context)
                    .load(Constants.IMAGE_URL + listItem.posterPath)
                    .error(R.drawable.default_placeholder)
                    .into(
                        IdlingResourceTarget(imagePoster)
                    )
            }

            itemView.setOnClickListener{listener(listItem)}
        }
    }
}

