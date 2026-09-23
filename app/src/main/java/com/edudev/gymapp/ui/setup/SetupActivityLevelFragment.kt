package com.edudev.gymapp.ui.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.edudev.gymapp.R
import com.edudev.gymapp.databinding.FragmentSetupActivityLevelBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupActivityLevelFragment : Fragment() {
    private var _binding: FragmentSetupActivityLevelBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SetupViewModel by activityViewModels()
    private var selectedLabel = "Advance" // preseleccionado según la referencia

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentSetupActivityLevelBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, s: Bundle?) {
        super.onViewCreated(view, s)
        binding.topBar.root.setOnClickListener { findNavController().navigateUp() }

        val options = listOf(
            binding.optBeginner to "Beginner",
            binding.optIntermediate to "Intermediate",
            binding.optAdvance to "Advance"
        )
        options.forEach { (view, label) -> view.setOnClickListener { select(view, label, options.map { it.first }) } }

        binding.btnContinue.setOnClickListener {
            viewModel.setActivityLevel(selectedLabel)
            findNavController().navigate(SetupActivityLevelFragmentDirections.actionActivityLevelToFillProfile())
        }
    }

    private fun select(view: TextView, label: String, all: List<TextView>) {
        all.forEach {
            it.setBackgroundResource(R.drawable.bg_pill_white)
            it.setTextColor(resources.getColor(R.color.brand_purple, null))
        }
        view.setBackgroundResource(R.drawable.bg_pill_lime)
        view.setTextColor(resources.getColor(R.color.auth_label_dark, null))
        selectedLabel = label
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}