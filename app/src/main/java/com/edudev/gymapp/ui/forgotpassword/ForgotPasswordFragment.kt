package com.edudev.gymapp.ui.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.edudev.gymapp.R
import com.edudev.gymapp.databinding.FragmentForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBar.tvTopBarTitle.text = getString(R.string.forgot_password_title)
        binding.topBar.ivBack.setOnClickListener { findNavController().navigateUp() }

        // TODO Fase 2.1: cuando exista POST /api/auth/forgot-password en el swagger,

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordToSetPassword())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}