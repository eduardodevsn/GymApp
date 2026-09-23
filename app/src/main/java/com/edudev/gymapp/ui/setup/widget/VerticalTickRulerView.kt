package com.edudev.gymapp.ui.setup.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.edudev.gymapp.R

class VerticalTickRulerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val density = context.resources.displayMetrics.density
    private val tickSpacingPx = 14f * density

    private val tickPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        alpha = 150
        strokeWidth = 2f * density
    }

    private val centerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.brand_lime)
        strokeWidth = 4f * density
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val w = width.toFloat()
        val left = w * 0.25f
        val right = w * 0.75f

        var y = 0f
        while (y <= height) {
            canvas.drawLine(left, y, right, y, tickPaint)
            y += tickSpacingPx
        }

        val centerY = height / 2f
        canvas.drawLine(0f, centerY, w, centerY, centerPaint)
    }
}