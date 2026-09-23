package com.edudev.gymapp.ui.setup.widget

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edudev.gymapp.R

class VerticalNumberScrollAdapter(
    private val range: IntRange,
    private val step: Int
) : RecyclerView.Adapter<VerticalNumberScrollAdapter.VH>() {

    var selectedValue: Int = range.first
        private set

    class VH(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_number_scroll_vertical, parent, false) as TextView
        return VH(view)
    }

    override fun getItemCount() = (range.last - range.first) / step + 1

    override fun onBindViewHolder(holder: VH, position: Int) {
        val value = range.first + position * step
        holder.textView.text = value.toString()
        val isSelected = value == selectedValue

        holder.textView.setTextColor(
            holder.textView.context.getColor(if (isSelected) R.color.white else R.color.ruler_number_dim)
        )
        holder.textView.textSize = if (isSelected) 26f else 18f
        holder.textView.setTypeface(null, if (isSelected) Typeface.BOLD else Typeface.NORMAL)
    }

    fun updateSelected(position: Int) {
        selectedValue = range.first + position * step
        notifyDataSetChanged()
    }

    fun positionOf(value: Int) = ((value - range.first) / step).coerceIn(0, itemCount - 1)
}