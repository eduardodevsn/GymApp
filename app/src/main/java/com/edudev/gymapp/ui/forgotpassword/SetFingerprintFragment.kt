package com.edudev.gymapp.ui.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.edudev.gymapp.R
import com.edudev.gymapp.databinding.FragmentSetFingerprintBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetFingerprintFragment : Fragment() {

    private var _binding: FragmentSetFingerprintBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetFingerprintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBar.tvTopBarTitle.text = getString(R.string.set_fingerprint_title)
        binding.topBar.ivBack.setOnClickListener { findNavController().navigateUp() }

        // TODO: acá va BiometricPrompt real cuando encaremos esa feature.
        binding.btnSkip.setOnClickListener { goToLogin() }
        binding.btnContinue.setOnClickListener { goToLogin() }
    }

    private fun goToLogin() {
        findNavController().navigate(SetFingerprintFragmentDirections.actionSetFingerprintToLogin())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}