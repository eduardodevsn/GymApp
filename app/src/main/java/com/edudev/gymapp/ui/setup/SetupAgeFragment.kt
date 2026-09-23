package com.edudev.gymapp.ui.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.edudev.gymapp.databinding.FragmentSetupAgeBinding
import com.edudev.gymapp.ui.setup.widget.setupNumberScroll
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupAgeFragment : Fragment() {
    private var _binding: FragmentSetupAgeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SetupViewModel by activityViewModels()
    private var currentAge = 28

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentSetupAgeBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, s: Bundle?) {
        super.onViewCreated(view, s)
        binding.topBar.root.setOnClickListener { findNavController().navigateUp() }

        currentAge = viewModel.state.value.age
        binding.tvBigValue.text = currentAge.toString()

        binding.rvAge.setupNumberScroll(14..80, currentAge) { value ->
            currentAge = value
            binding.tvBigValue.text = value.toString()
        }

        binding.btnContinue.setOnClickListener {
            viewModel.setAge(currentAge)
            findNavController().navigate(SetupAgeFragmentDirections.actionAgeToWeight())
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}