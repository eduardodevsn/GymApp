package com.edudev.gymapp.ui.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.edudev.gymapp.databinding.FragmentSetupHeightBinding
import com.edudev.gymapp.ui.setup.widget.setupVerticalNumberScroll
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupHeightFragment : Fragment() {
    private var _binding: FragmentSetupHeightBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SetupViewModel by activityViewModels()
    private var currentHeight = 165

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentSetupHeightBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, s: Bundle?) {
        super.onViewCreated(view, s)
        binding.topBar.root.setOnClickListener { findNavController().navigateUp() }

        currentHeight = viewModel.state.value.heightCm
        binding.tvBigValue.text = currentHeight.toString()

        // Lista muestra de a 5cm (155, 160, 165...); el valor grande arriba siempre
        // refleja el múltiplo de 5 seleccionado. Si más adelante hace falta precisión
        // de 1cm, se cambia el "step" acá y en SetupViewModel el rango correspondiente.
        binding.rvHeight.setupVerticalNumberScroll(range = 140..210, step = 5, initialValue = currentHeight) { value ->
            currentHeight = value
            binding.tvBigValue.text = value.toString()
        }

        binding.btnContinue.setOnClickListener {
            viewModel.setHeight(currentHeight)
            findNavController().navigate(SetupHeightFragmentDirections.actionHeightToGoal())
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}