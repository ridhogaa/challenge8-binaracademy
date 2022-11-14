package com.ergea.challengetopeight.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.ergea.challengetopeight.R
import com.ergea.challengetopeight.databinding.FragmentRegisterBinding
import com.ergea.challengetopeight.data.local.database.UserEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by activityViewModels()
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toRegister()
    }

    private fun toRegister() {
        binding.btnRegister.setOnClickListener {
            val username = binding.etUsernameRegister.text.toString().trim()
            val email = binding.etEmailRegister.text.toString().trim()
            val password = binding.etPasswordRegister.text.toString().trim()
            val confirmPassword = binding.etKonfirmPasswordRegister.text.toString().trim()
            if (username.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty() || confirmPassword.isNotEmpty()) {

                if (password.equals(confirmPassword, ignoreCase = false)) {
                    if (viewModel.getIfUserExistsByEmail(email)) {
                        Toast.makeText(
                            requireContext(),
                            "User already registered, Sign up again!",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.apply {
                            etEmailRegister.text?.clear()
                            etUsernameRegister.text?.clear()
                            etPasswordRegister.text?.clear()
                            etKonfirmPasswordRegister.text?.clear()
                        }
                    } else {
                        viewModel.insertUser(
                            UserEntity(
                                username = username,
                                email = email,
                                password = password,
                                namaLengkap = "",
                                tanggalLahir = "",
                                alamat = ""
                            )
                        )
                        Toast.makeText(requireContext(), "Register Success", Toast.LENGTH_SHORT)
                            .show()
                        it.findNavController()
                            .navigate(R.id.action_registerFragment_to_loginFragment)
                    }
                } else {
                    Toast.makeText(requireContext(), "Password not matching", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(requireContext(), "Empty Field!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}