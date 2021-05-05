package com.irfan.moviecatalogue.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irfan.moviecatalogue.R
import com.irfan.moviecatalogue.databinding.ItemMovieBinding
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import com.irfan.moviecatalogue.utils.Constants.IMAGE_URL

class ListMovieAdapter(
    private val listItem: MutableList<MovieResponse>,
    private val listener: (Any) -> Unit
) : RecyclerView.Adapter<ListMovieAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ListMovieAdapter.MainViewHolder, position: Int) {
        holder.bind(listItem[position], listener)
    }

    override fun getItemCount(): Int = listItem.size

    inner class MainViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemMovieBinding.bind(view)

        fun bind(listItem: MovieResponse, listener: (Any) -> Unit) {
            binding.apply {
                tvTitle.text = if (listItem.original_title.isNullOrBlank()) listItem.original_name else listItem.original_title
                tvRelease.text = if (listItem.first_air_date.isNullOrBlank()) listItem.release_date else listItem.first_air_date
                tvOverview.text = listItem.overview
                Glide.with(view.context)
                    .load(IMAGE_URL + listItem.poster_path)
                    .into(imagePoster)
            }
            itemView.setOnClickListener { listener(listItem) }
        }
    }
}