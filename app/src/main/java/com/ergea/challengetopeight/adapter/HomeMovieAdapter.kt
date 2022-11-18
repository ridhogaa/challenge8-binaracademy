package com.ergea.challengetopeight.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ergea.challengetopeight.R
import com.ergea.challengetopeight.data.remote.model.ResultMovies
import com.ergea.challengetopeight.databinding.MovieListBinding
import com.ergea.challengetopeight.databinding.MovieListBinding.inflate

class HomeMovieAdapter(private val movies: List<ResultMovies>) :
    RecyclerView.Adapter<HomeMovieAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: MovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val imageBaseUrl = "https://image.tmdb.org/t/p/w500/"
        val i = ""
        @SuppressLint("SetTextI18n")
        fun bind(listData: ResultMovies) {
            binding.titleMovie.text = listData.title
            binding.releaseMovie.text = "Release at: " + listData.releaseDate
            Glide.with(itemView).load(imageBaseUrl + listData.posterPath).into(binding.imgMovie)
            binding.cardView.setOnClickListener {
                val bundle = Bundle().apply {
                    putInt("ID", listData.id.toString().toInt())
                }
                it.findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movies[position])
    }

}