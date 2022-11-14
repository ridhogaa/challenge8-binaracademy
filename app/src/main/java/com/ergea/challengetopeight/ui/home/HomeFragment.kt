package com.ergea.challengetopeight.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ergea.challengetopeight.R
import com.ergea.challengetopeight.adapter.HomeMovieAdapter
import com.ergea.challengetopeight.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toProfile()
        setUpObserver()
    }

    @SuppressLint("SetTextI18n")
    private fun setUpObserver() {
        viewModel.getDataStoreId().observe(viewLifecycleOwner) {
            viewModel.getUserById(it)
        }
        viewModel.user.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.txtWelcomeUsername.text = "Hi, ${it.username}"
            }
        }
        viewModel.movie.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.movieRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.movieRecyclerView.setHasFixedSize(true)
                binding.movieRecyclerView.adapter = HomeMovieAdapter(it)
            }
        }
    }

    private fun toProfile() {
        binding.imgProfile.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}