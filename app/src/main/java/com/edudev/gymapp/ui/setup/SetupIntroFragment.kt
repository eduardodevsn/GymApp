package com.edudev.gymapp.ui.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.edudev.gymapp.databinding.FragmentSetupIntroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupIntroFragment : Fragment() {
    private var _binding: FragmentSetupIntroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentSetupIntroBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, s: Bundle?) {
        super.onViewCreated(view, s)
        binding.btnNext.setOnClickListener {
            findNavController().navigate(SetupIntroFragmentDirections.actionSetupIntroToGender())
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}