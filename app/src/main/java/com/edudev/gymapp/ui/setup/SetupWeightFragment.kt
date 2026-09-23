package com.edudev.gymapp.ui.setup

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.edudev.gymapp.databinding.FragmentSetupWeightBinding
import com.edudev.gymapp.ui.setup.widget.setupNumberScroll
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupWeightFragment : Fragment() {
    private var _binding: FragmentSetupWeightBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SetupViewModel by activityViewModels()
    private var currentValue = 75
    private var currentUnit = WeightUnit.KG

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentSetupWeightBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, s: Bundle?) {
        super.onViewCreated(view, s)
        binding.topBar.root.setOnClickListener { findNavController().navigateUp() }

        currentValue = viewModel.state.value.weightValue
        currentUnit = viewModel.state.value.weightUnit
        updateBigValue()

        binding.rvWeight.setupNumberScroll(30..200, currentValue) { value ->
            currentValue = value
            updateBigValue()
        }

        binding.tvKg.setOnClickListener { selectUnit(WeightUnit.KG) }
        binding.tvLb.setOnClickListener { selectUnit(WeightUnit.LB) }
        selectUnit(currentUnit)

        binding.btnContinue.setOnClickListener {
            viewModel.setWeight(currentValue, currentUnit)
            findNavController().navigate(SetupWeightFragmentDirections.actionWeightToHeight())
        }
    }

    private fun selectUnit(unit: WeightUnit) {
        currentUnit = unit
        binding.tvKg.setTypeface(null, if (unit == WeightUnit.KG) Typeface.BOLD else Typeface.NORMAL)
        binding.tvLb.setTypeface(null, if (unit == WeightUnit.LB) Typeface.BOLD else Typeface.NORMAL)
        updateBigValue()
    }

    private fun updateBigValue() {
        binding.tvBigValue.text = currentValue.toString()
        binding.tvBigValueUnit.text = if (currentUnit == WeightUnit.KG) "Kg" else "Lb"
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}