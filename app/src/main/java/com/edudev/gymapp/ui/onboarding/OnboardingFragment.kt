package com.edudev.gymapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.edudev.gymapp.R
import com.edudev.gymapp.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private val pages = OnboardingContent.pages()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = OnboardingPagerAdapter(pages)
        setupDots()

        binding.tvSkip.setOnClickListener { goToLogin() }
        binding.btnAction.setOnClickListener {
            val current = binding.viewPager.currentItem
            if (current == pages.lastIndex) {
                goToLogin()
            } else {
                binding.viewPager.currentItem = current + 1
            }
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateOverlayForPage(position)
            }
        })

        updateOverlayForPage(0)
    }

    private fun setupDots() {
        binding.dotsContainer.removeAllViews()
        repeat(pages.size - 1) {
            val dot = ImageView(requireContext()).apply {
                setImageResource(R.drawable.dot_inactive)
                layoutParams = ViewGroup.MarginLayoutParams(24, 24).apply {
                    marginStart = 6
                }
            }
            binding.dotsContainer.addView(dot)
        }
    }

    private fun updateOverlayForPage(position: Int) {
        val isWelcome = pages[position].isWelcomePage
        val isLast = position == pages.lastIndex

        binding.tvSkip.visibility = if (isWelcome || isLast) View.GONE else View.VISIBLE
        binding.dotsContainer.visibility = if (isWelcome) View.GONE else View.VISIBLE
        binding.btnAction.visibility = if (isWelcome) View.GONE else View.VISIBLE
        binding.btnAction.text = getString(
            if (isLast) R.string.onboarding_get_started else R.string.onboarding_next
        )

        for (i in 0 until binding.dotsContainer.childCount) {
            val dot = binding.dotsContainer.getChildAt(i) as ImageView
            dot.setImageResource(if (i == position - 1) R.drawable.dot_active else R.drawable.dot_inactive)
        }
    }

    private fun goToLogin() {
        findNavController().navigate(OnboardingFragmentDirections.actionOnboardingToLogin())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}