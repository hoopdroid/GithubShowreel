package com.github.codechallenge.presentation.commits.chart

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.LinearLayout
import com.github.codechallenge.R
import com.github.codechallenge.base.utils.toPx
import com.github.codechallenge.base.utils.toPxF
import com.github.codechallenge.base.utils.toSpF

internal class CommitsChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    companion object {
        private const val ANIMATION_DURATION = 500L
        private const val INTERPOLATOR_TENSION = 0.5f
        private val CORNER_RADIUS = 4.toPxF()
        private val VERTICAL_PADDING = 24.toPxF()
        private val HORIZONTAL_PADDING = 8.toPxF()
        private val TEXT_SIZE = 12.toSpF()
        private val DEFAULT_HEIGHT = 200.toPx()
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.getColor(R.color.white)
        textAlign = Paint.Align.CENTER
        textSize = TEXT_SIZE
    }

    private val defaultTrackColor = Paint(Paint.ANTI_ALIAS_FLAG)
        .apply {
            style = Paint.Style.FILL
            color = context.getColor(R.color.githubColor)
        }

    private val backgroundColor = Paint(Paint.ANTI_ALIAS_FLAG)
        .apply {
            style = Paint.Style.FILL
            color = context.getColor(R.color.githubBackground)
        }

    private var progress: Float = 0F
    private var dateText: String? = ""
    private var commitsText: String? = ""

    fun bind(newProgress: Float, dateText: String, commitsText: String) {
        this.dateText = dateText
        this.commitsText = commitsText

        ValueAnimator.ofFloat(progress, newProgress).apply {
            duration = ANIMATION_DURATION
            interpolator = OvershootInterpolator(INTERPOLATOR_TENSION)
            addUpdateListener { valueAnimator ->
                progress = valueAnimator.animatedValue as Float
                invalidate()
            }
        }
            .start()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = if (layoutParams.width == LinearLayout.LayoutParams.WRAP_CONTENT) {
            MeasureSpec.makeMeasureSpec(
                getViewWidth(),
                MeasureSpec.AT_MOST
            )
        } else {
            widthMeasureSpec
        }
        val height = if (layoutParams.height == LinearLayout.LayoutParams.WRAP_CONTENT) {
            MeasureSpec.makeMeasureSpec(
                DEFAULT_HEIGHT,
                MeasureSpec.AT_MOST
            )
        } else {
            heightMeasureSpec
        }
        super.onMeasure(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        with(canvas) {
            val value = 1 - (progress / 100)
            val top = value * (height - VERTICAL_PADDING)

            drawRoundRect(
                HORIZONTAL_PADDING,
                VERTICAL_PADDING,
                width.toFloat() - HORIZONTAL_PADDING,
                height.toFloat() - VERTICAL_PADDING,
                CORNER_RADIUS,
                CORNER_RADIUS,
                backgroundColor,
            )

            drawRoundRect(
                HORIZONTAL_PADDING,
                top,
                width.toFloat() - HORIZONTAL_PADDING,
                height.toFloat() - VERTICAL_PADDING,
                CORNER_RADIUS,
                CORNER_RADIUS,
                defaultTrackColor
            )

            dateText?.let {
                drawText(
                    it,
                    width.toFloat() / 2,
                    height.toFloat() - CORNER_RADIUS,
                    textPaint
                )
            }

            commitsText?.let {
                drawText(
                    it,
                    (width / 2).toFloat(),
                    0f + VERTICAL_PADDING / 2,
                    textPaint
                )
            }
        }
    }

    private fun getViewWidth(): Int {
        val text =
            if ((commitsText?.length ?: 0) > (dateText?.length ?: 0)) commitsText else dateText
        val bounds = Rect()
        textPaint.getTextBounds(text, 0, text?.length ?: 0, bounds)

        return bounds.width() + HORIZONTAL_PADDING.toInt()
    }
}