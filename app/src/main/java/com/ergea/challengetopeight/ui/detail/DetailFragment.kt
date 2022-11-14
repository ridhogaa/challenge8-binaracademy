package com.ergea.challengetopeight.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.ergea.challengetopeight.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by activityViewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt("ID")
        if (id != null) {
            viewModel.getMovieDetail(id.toInt())
            observeDetailMovie()
        }
    }

    private fun observeDetailMovie() {
        val baseURL = "https://image.tmdb.org/t/p/w500/"
        viewModel.movie.observe(viewLifecycleOwner) {
            binding.apply {
                if (it != null) {
                    txtTitle2.text = it.body()!!.title.toString()
                    releaseDate.text = it.body()!!.releaseDate.toString()
                    Glide.with(requireContext())
                        .load(baseURL + it.body()!!.backdropPath)
                        .into(binding.backdropPath)
                    Glide.with(requireContext())
                        .load(baseURL + it.body()!!.posterPath)
                        .into(binding.imgPosterPath)
                    overview.text = it.body()!!.overview.toString()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}