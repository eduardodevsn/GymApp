package com.edudev.gymapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.edudev.gymapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBar.tvTopBarTitle.text = getString(com.edudev.gymapp.R.string.login_title)
        binding.topBar.ivBack.setOnClickListener { findNavController().navigateUp() }

        binding.btnLogin.setOnClickListener {
            viewModel.login(
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )
        }

        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginToForgotPassword())
        }

        binding.tvNoAccount.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginToRegister())
        }

        // TODO: sin endpoints de OAuth/biometría en el swagger todavía
        val notImplemented = { Toast.makeText(requireContext(), "Próximamente", Toast.LENGTH_SHORT).show() }
        binding.btnGoogle.setOnClickListener { notImplemented() }
        binding.btnBiometric.setOnClickListener { notImplemented() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                    binding.btnLogin.isEnabled = !state.isLoading

                    binding.tvError.visibility = if (state.error != null) View.VISIBLE else View.GONE
                    binding.tvError.text = state.error

                    if (state.loggedIn) {
                        findNavController().navigate(LoginFragmentDirections.actionLoginToHome())
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}