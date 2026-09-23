package com.edudev.gymapp.ui.setup.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.edudev.gymapp.R

class TickRulerView @JvmOverloads constructor(
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
        val h = height.toFloat()
        val top = h * 0.2f
        val bottom = h * 0.8f

        var x = 0f
        while (x <= width) {
            canvas.drawLine(x, top, x, bottom, tickPaint)
            x += tickSpacingPx
        }

        val centerX = width / 2f
        canvas.drawLine(centerX, 0f, centerX, h, centerPaint)
    }
}