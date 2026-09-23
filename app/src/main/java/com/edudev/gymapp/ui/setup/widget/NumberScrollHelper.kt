package com.edudev.gymapp.ui.setup.widget

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setupNumberScroll(
    range: IntRange,
    initialValue: Int,
    onValueSelected: (Int) -> Unit
): NumberScrollAdapter {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    val adapter = NumberScrollAdapter(range)
    this.adapter = adapter
    val snapHelper = LinearSnapHelper()
    snapHelper.attachToRecyclerView(this)

    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(rv: RecyclerView, newState: Int) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                val snapView = snapHelper.findSnapView(layoutManager) ?: return
                val position = layoutManager!!.getPosition(snapView)
                adapter.updateSelected(position)
                onValueSelected(adapter.selectedValue)
            }
        }
    })

    post { scrollToPosition(adapter.positionOf(initialValue)) }
    return adapter
}

fun RecyclerView.setupVerticalNumberScroll(
    range: IntRange,
    step: Int,
    initialValue: Int,
    onValueSelected: (Int) -> Unit
): VerticalNumberScrollAdapter {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    val adapter = VerticalNumberScrollAdapter(range, step)
    this.adapter = adapter
    val snapHelper = LinearSnapHelper()
    snapHelper.attachToRecyclerView(this)

    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(rv: RecyclerView, newState: Int) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                val snapView = snapHelper.findSnapView(layoutManager) ?: return
                val position = layoutManager!!.getPosition(snapView)
                adapter.updateSelected(position)
                onValueSelected(adapter.selectedValue)
            }
        }
    })

    post { scrollToPosition(adapter.positionOf(initialValue)) }
    return adapter
}