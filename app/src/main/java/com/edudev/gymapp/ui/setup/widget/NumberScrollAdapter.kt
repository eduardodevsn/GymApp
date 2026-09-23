package com.edudev.gymapp.ui.setup.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edudev.gymapp.R

class NumberScrollAdapter(
    private val range: IntRange
) : RecyclerView.Adapter<NumberScrollAdapter.VH>() {

    var selectedValue: Int = range.first
        private set

    class VH(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_number_scroll, parent, false) as TextView
        return VH(view)
    }

    override fun getItemCount() = range.count()

    override fun onBindViewHolder(holder: VH, position: Int) {
        val value = range.first + position
        holder.textView.text = value.toString()
        val isSelected = value == selectedValue

        holder.textView.setTextColor(
            holder.textView.context.getColor(
                if (isSelected) R.color.white else R.color.ruler_number_dim
            )
        )
        holder.textView.textSize = if (isSelected) 26f else 18f
        holder.textView.setTypeface(holder.textView.typeface, if (isSelected) android.graphics.Typeface.BOLD else android.graphics.Typeface.NORMAL)
    }

    fun updateSelected(position: Int) {
        selectedValue = range.first + position
        notifyDataSetChanged()
    }

    fun positionOf(value: Int) = (value - range.first).coerceIn(0, itemCount - 1)
}