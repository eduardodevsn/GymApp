package com.edudev.gymapp.ui.onboarding

data class OnboardingPageData(
    val imageResId: Int,
    val iconResId: Int?,
    val titleResId: Int?,
    val isWelcomePage: Boolean = false
)

object OnboardingContent {
    fun pages() = listOf(
        OnboardingPageData(
            imageResId = com.edudev.gymapp.R.drawable.onboarding4,
            iconResId = null,
            titleResId = null,
            isWelcomePage = true
        ),
        OnboardingPageData(
            imageResId = com.edudev.gymapp.R.drawable.onboarding1,
            iconResId = com.edudev.gymapp.R.drawable.ic_cardio_active,
            titleResId = com.edudev.gymapp.R.string.onboarding_page_1_title
        ),
        OnboardingPageData(
            imageResId = com.edudev.gymapp.R.drawable.onboarding2,
            iconResId = com.edudev.gymapp.R.drawable.ic_cup_active,
            titleResId = com.edudev.gymapp.R.string.onboarding_page_2_title
        ),
        OnboardingPageData(
            imageResId = com.edudev.gymapp.R.drawable.onboarding3,
            iconResId = com.edudev.gymapp.R.drawable.ic_user_active,
            titleResId = com.edudev.gymapp.R.string.onboarding_page_3_title
        )
    )
}