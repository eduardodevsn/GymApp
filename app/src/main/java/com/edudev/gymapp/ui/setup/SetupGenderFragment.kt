package com.edudev.gymapp.ui.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.edudev.gymapp.R
import com.edudev.gymapp.databinding.FragmentSetupGenderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupGenderFragment : Fragment() {
    private var _binding: FragmentSetupGenderBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SetupViewModel by activityViewModels()
    private var selected = "FEMALE" // preseleccionado según la referencia visual

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentSetupGenderBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, s: Bundle?) {
        super.onViewCreated(view, s)
        binding.topBar.root.setOnClickListener { findNavController().navigateUp() }

        binding.ivMale.setOnClickListener { select("MALE") }
        binding.ivFemale.setOnClickListener { select("FEMALE") }
        select(selected)

        binding.btnContinue.setOnClickListener {
            viewModel.setGender(selected)
            findNavController().navigate(SetupGenderFragmentDirections.actionGenderToAge())
        }
    }

    private fun select(gender: String) {
        selected = gender
        binding.ivMale.setBackgroundResource(if (gender == "MALE") R.drawable.bg_circle_lime else R.drawable.bg_circle_dark)
        binding.ivFemale.setBackgroundResource(if (gender == "FEMALE") R.drawable.bg_circle_lime else R.drawable.bg_circle_dark)
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}