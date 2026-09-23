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
import com.edudev.gymapp.databinding.FragmentSetupGoalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupGoalFragment : Fragment() {
    private var _binding: FragmentSetupGoalBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SetupViewModel by activityViewModels()
    private var selectedOption: TextView? = null
    private var selectedGoal = ""

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentSetupGoalBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, s: Bundle?) {
        super.onViewCreated(view, s)
        binding.topBar.root.setOnClickListener { findNavController().navigateUp() }

        val options = listOf(
            binding.optLoseWeight to getString(R.string.goal_lose_weight),
            binding.optGainWeight to getString(R.string.goal_gain_weight),
            binding.optMuscleGain to getString(R.string.goal_muscle_gain),
            binding.optShapeBody to getString(R.string.goal_shape_body),
            binding.optOthers to getString(R.string.goal_others)
        )
        options.forEach { (view, label) ->
            view.setOnClickListener { select(view, label, options.map { it.first }) }
        }

        binding.btnContinue.setOnClickListener {
            if (selectedGoal.isNotEmpty()) viewModel.setObjective(selectedGoal)
            findNavController().navigate(SetupGoalFragmentDirections.actionGoalToActivityLevel())
        }
    }

    private fun select(view: TextView, label: String, all: List<TextView>) {
        all.forEach { it.setBackgroundResource(R.drawable.bg_pill_white) }
        view.setBackgroundResource(R.drawable.bg_pill_lime)
        selectedOption = view
        selectedGoal = label
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}