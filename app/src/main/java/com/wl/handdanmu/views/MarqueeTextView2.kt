package com.wl.handdanmu.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View


class MarqueeTextView2(context: Context, attrs: AttributeSet?) : View(context, attrs), ITextSetUp {
    val default = "This is danMu"
    private var text = default

    // 要显示的文本
    private var textColor = Color.WHITE // 文本颜色
    private var textSize = 60f // 文本大小
    private val textPaint = Paint()
    private var textX = 0f // 文本的X坐标位置
    private val defaultSpeed = 10f
    private var scrollSpeed = defaultSpeed // 滚动速度，可以根据需要调整

    private var viewWidth = 0
    val duration = 20L

    private var textY = 0f

    init {
        textPaint.color = textColor
        textPaint.textSize = textSize
        textPaint.isAntiAlias = true
        textX = 0f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textY = (height / 2).toFloat() + getTxtHeight() / 2
        canvas.drawText(text, textX, textY, textPaint)
    }

    var textWidth = 0f
    fun startMarquee() {
        var textWidth: Float
        val marqueeRunnable = object : Runnable {
            override fun run() {
                textX -= scrollSpeed
                textWidth = textPaint.measureText(text)
                if (textX < -textWidth ) {
                    textX = viewWidth.toFloat()
                }
                invalidate()
                postDelayed(this, duration)
            }
        }
        removeCallbacks(marqueeRunnable)
        postDelayed(marqueeRunnable, duration)
    }

    override fun setTextSize(size: Float) {
        textSize = size * 4
        textPaint.textSize = textSize
        textWidth = textPaint.measureText(text)
        textY = (height / 2).toFloat() + getTxtHeight() / 2
    }

    private fun getTxtHeight(): Int {
        return textSize.toInt()// textBounds.height()
    }

    override fun setText(txt: String) {
        text = txt
        if (txt.isEmpty()){
            text = default
        }
        textWidth = textPaint.measureText(text)
    }

    override fun setScrollSpeed(speed: Float) {
        scrollSpeed = speed * defaultSpeed
        scrollSpeed = Math.max(scrollSpeed, defaultSpeed)
    }

    override fun setTextColor(color: Int) {
        textColor = color
        textPaint.color = textColor
    }
}
