package com.wl.handdanmu.views


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.wl.handdanmu.ScreenUtils
import com.wl.handdanmu.TextMeasurementUtils

class CustomVerticalTextView(context: Context, attrs: AttributeSet?) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {
    private var textColor: Int = Color.BLACK
    private var textSize: Float = 16f
    private var textStyle: Int = Typeface.NORMAL

    private var currentY: Float = 0f
    private var viewHeight: Float = 0f

    private fun obtainScrollAnimation(): Animation {
        val fromY =
            ScreenUtils.getScreenWidth(context).toFloat() + TextMeasurementUtils.measureTextWidth(
                text.toString(),
                textSize
            )
        val toY = -fromY

        return TranslateAnimation(0f, 0f, fromY, toY).apply {
            duration = 3000 // 设置滚动速度，单位为毫秒
            fillAfter = true
            repeatCount = Animation.INFINITE // 无限循环滚动
        }
    }

    init {
        startAnimation(obtainScrollAnimation())
    }

    fun setUpText(text: String) {
        this.text = text
        startAnimation(obtainScrollAnimation())
    }

    var paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let {
            viewHeight = height.toFloat()

            paint.color = textColor
            paint.textSize = textSize
            paint.typeface = Typeface.defaultFromStyle(textStyle)

            val textLength = text.length
            val textHeight = paint.descent() - paint.ascent()

            var currentX = 0f
            for (i in 0 until textLength) {
                val textChar = text[i].toString()
                val textWidth = paint.measureText(textChar)
                it.save()
                it.drawText(textChar, currentX, currentY, paint)
                it.restore()

                currentX += textWidth
                currentY += textHeight
            }

            if (currentY <= -viewHeight) {
                currentY = viewHeight
            }

            invalidate()
        }
    }
}

