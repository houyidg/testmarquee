package com.wl.handdanmu.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MarqueeTextView2(context: Context, attrs: AttributeSet?) : View(context, attrs), ITextSetUp {
    private var text ="This is danMu"
         // 要显示的文本
    private var textColor = Color.WHITE // 文本颜色
    private var textSize = 60f // 文本大小
    private val textPaint = Paint()
    private var textX = 0f // 文本的X坐标位置
    private val defaultSpeed = 20f
    private var scrollSpeed = defaultSpeed // 滚动速度，可以根据需要调整

    private var viewWidth = 0

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
        canvas.drawText(text, textX, (height / 2).toFloat(), textPaint)
    }

    fun startMarquee() {
        val textWidth = textPaint.measureText(text)
        val duration = 50
        val marqueeRunnable = object : Runnable {
            override fun run() {
                textX -= scrollSpeed
                if (textX + textWidth < 0) {
                    textX = viewWidth.toFloat()
                }
                invalidate()
                postDelayed(this, 16)
            }
        }
        removeCallbacks(marqueeRunnable)
        postDelayed(marqueeRunnable, duration.toLong())
    }

    override fun setTextSize(size: Float) {
        textSize = size*4
        textPaint.textSize = textSize
    }

    override fun setText(txt: String) {
        text = txt
    }

    override fun setScrollSpeed(speed: Float) {
        scrollSpeed = speed*defaultSpeed
        scrollSpeed = Math.max(scrollSpeed,defaultSpeed)
    }

    override fun setTextColor(color: Int) {
        textColor = color
        textPaint.color = textColor
    }
}
