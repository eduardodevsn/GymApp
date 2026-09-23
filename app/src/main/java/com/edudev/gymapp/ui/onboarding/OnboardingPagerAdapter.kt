package com.edudev.gymapp.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edudev.gymapp.databinding.ItemOnboardingPageBinding
import com.edudev.gymapp.databinding.ItemOnboardingWelcomeBinding

private const val VIEW_TYPE_WELCOME = 0
private const val VIEW_TYPE_PAGE = 1

class OnboardingPagerAdapter(
    private val pages: List<OnboardingPageData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int) =
        if (pages[position].isWelcomePage) VIEW_TYPE_WELCOME else VIEW_TYPE_PAGE

    override fun getItemCount() = pages.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_WELCOME) {
            WelcomeViewHolder(ItemOnboardingWelcomeBinding.inflate(inflater, parent, false))
        } else {
            PageViewHolder(ItemOnboardingPageBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val page = pages[position]
        if (holder is PageViewHolder) {
            holder.binding.ivPhoto.setImageResource(page.imageResId)
            page.iconResId?.let { holder.binding.ivIcon.setImageResource(it) }
            page.titleResId?.let { holder.binding.tvTitle.setText(it) }
        }
    }

    class WelcomeViewHolder(binding: ItemOnboardingWelcomeBinding) : RecyclerView.ViewHolder(binding.root)
    class PageViewHolder(val binding: ItemOnboardingPageBinding) : RecyclerView.ViewHolder(binding.root)
}