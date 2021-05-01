package com.irfan.moviecatalogue.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irfan.moviecatalogue.R
import com.irfan.moviecatalogue.databinding.ItemMovieBinding
import com.irfan.moviecatalogue.data.source.local.entity.Movie

class ListMovieAdapter(
    private val listItem: MutableList<Movie>,
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

        fun bind(listItem: Movie, listener: (Any) -> Unit) {
            binding.apply {
                tvTitle.text = listItem.title
                tvRelease.text = listItem.release
                tvOverview.text = listItem.overview

                Glide.with(view.context)
                    .load(getDrawableResource(listItem.posterImg?:""))
                    .into(imagePoster)
            }

            itemView.setOnClickListener { listener(listItem) }
        }

        private fun getDrawableResource(drawableName: String) = view.context.resources.getIdentifier(drawableName, null, view.context.packageName)

    }

}