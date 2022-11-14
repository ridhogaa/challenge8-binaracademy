package com.ergea.challengetopeight.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.ergea.challengetopeight.R
import com.ergea.challengetopeight.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by activityViewModels()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        viewModel.getDataStoreId().observe(viewLifecycleOwner) {
            if (it != null) {
                updateUser(it)
                viewModel.getUserById(it)
            }
        }
        logout()
    }

    private fun updateUser(id: Int) {
        binding.btnUpdate.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val namaLengkap = binding.etNamaLengkap.text.toString().trim()
            val tanggalLahir = binding.etTanggalLahir.text.toString().trim()
            val alamat = binding.etAlamat.text.toString().trim()
            viewModel.updateUser(id, username, namaLengkap, tanggalLahir, alamat)
            Toast.makeText(requireContext(), "Update Success", Toast.LENGTH_SHORT).show()
        }
    }

    private fun logout() {
        binding.btnLogout.setOnClickListener {
            viewModel.removeIsLogin()
            viewModel.removeId()
            Toast.makeText(requireContext(), "Logout", Toast.LENGTH_SHORT).show()
            it.findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }

    private fun setUpObserver() {
        viewModel.user.observe(viewLifecycleOwner) { data ->
            binding.apply {
                if (data != null) {
                    etUsername.setText(data.username.toString())
                    etNamaLengkap.setText(data.namaLengkap.toString())
                    etTanggalLahir.setText(data.tanggalLahir.toString())
                    etAlamat.setText(data.alamat.toString())
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}