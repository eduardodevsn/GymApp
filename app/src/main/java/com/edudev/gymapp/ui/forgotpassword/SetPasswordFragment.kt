package com.edudev.gymapp.ui.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.edudev.gymapp.R
import com.edudev.gymapp.databinding.FragmentSetPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetPasswordFragment : Fragment() {

    private var _binding: FragmentSetPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBar.tvTopBarTitle.text = getString(R.string.set_password_title)
        binding.topBar.ivBack.setOnClickListener { findNavController().navigateUp() }

        binding.btnReset.setOnClickListener {
            val password = binding.etPassword.text.toString()
            val confirm = binding.etConfirmPassword.text.toString()

            if (password != confirm || password.isBlank()) {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = getString(R.string.passwords_dont_match)
                return@setOnClickListener
            }

            // TODO Fase 2.1: cuando exista POST /api/auth/reset-password, llamarlo acá.
            findNavController().navigate(SetPasswordFragmentDirections.actionSetPasswordToSetFingerprint())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}