package com.wl.handdanmu

import android.graphics.Paint

object TextMeasurementUtils {
    fun measureTextWidth(text: String, textSize: Float): Float {
        val paint = Paint()
        paint.textSize = textSize
        return paint.measureText(text)
    }
}
