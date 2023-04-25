package com.github.codechallenge.base.utils

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import kotlin.math.ceil

internal fun View.gone() {
    this.visibility = View.GONE
}

internal fun View.show() {
    this.visibility = View.VISIBLE
}

internal fun Int.toDp(): Int = ceil(this / Resources.getSystem().displayMetrics.density).toInt()

internal fun Int.toSpF(): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), Resources.getSystem().displayMetrics)

internal fun Int.toPx(): Int =
    toPxF().toInt()

internal fun Int.toPxF(): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)
