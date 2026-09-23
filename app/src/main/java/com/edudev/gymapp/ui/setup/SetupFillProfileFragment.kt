package com.edudev.gymapp.ui.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.edudev.gymapp.databinding.FragmentSetupFillProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SetupFillProfileFragment : Fragment() {
    private var _binding: FragmentSetupFillProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SetupViewModel by activityViewModels()

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentSetupFillProfileBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, s: Bundle?) {
        super.onViewCreated(view, s)
        binding.topBar.root.setOnClickListener { findNavController().navigateUp() }

        // TODO: precargar email real del usuario logueado (hoy no hay endpoint "me" simple sin

        binding.btnStart.setOnClickListener {
            viewModel.setFullName(binding.etFullName.text.toString())
            viewModel.setPhone(binding.etMobile.text.toString())
            viewModel.submit()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    binding.progressBar.visibility = if (state.isSubmitting) View.VISIBLE else View.GONE
                    binding.btnStart.isEnabled = !state.isSubmitting

                    binding.tvError.visibility = if (state.submitError != null) View.VISIBLE else View.GONE
                    binding.tvError.text = state.submitError

                    if (state.submitted) {
                        findNavController().navigate(SetupFillProfileFragmentDirections.actionFillProfileToHome())
                    }
                }
            }
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}