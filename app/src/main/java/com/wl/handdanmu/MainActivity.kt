package com.wl.handdanmu

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wl.handdanmu.databinding.ActivityMainBinding
import com.wl.handdanmu.databinding.LayoutMenuBinding


class MainActivity : AppCompatActivity() {
    private val mBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
//        mBinding.autoScrollTextView.apply {
//            setText("我是大哥xxa少时诵诗书少时诵诗书是撒是撒是撒休息休息休息休息")
//        }
        mBinding.tv.startMarquee()
        closeDrawer()
        mBinding.ivSetting.setOnClickListener {
            openDrawer()
        }

        mBinding.includeMenu.tvClose.setOnClickListener {
            closeDrawer()
        }

        mBinding.includeMenu.apply {
            tvFont0.setupFontClick(mBinding)
            tvFont1.setupFontClick(mBinding)
            tvFont15.setupFontClick(mBinding)
            tvFont2.setupFontClick(mBinding)
            tvFont3.setupFontClick(mBinding)
            tvFont4.setupFontClick(mBinding)

            tvFont0.callOnClick()

            // 添加 TextWatcher 监听文本变化
            // 添加 TextWatcher 监听文本变化
            etInputShowTxt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {
                    // 在文本变化之前执行操作
                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    // 在文本变化过程中执行操作
                    val text = charSequence.toString()
                    // 在这里可以对输入的文本进行处理
//                    mBinding.autoScrollTextView.setUpText(text)
                    mBinding.tv.setText(text)
                }

                override fun afterTextChanged(editable: Editable) {
                    // 在文本变化之后执行操作
                }
            })
        }
    }

    private fun TextView.setupFontClick(mBinding: ActivityMainBinding) {
        setOnClickListener {
            mBinding.includeMenu.setupFontDefault()
            isSelected = true
            mBinding.tv.setTextSize(text.toString().toFloat())
//            mBinding.autoScrollTextView.textSize = text.toString().toFloat()
        }
    }

    private fun LayoutMenuBinding.setupFontDefault() {
        tvFont0.isSelected = false
        tvFont1.isSelected = false
        tvFont15.isSelected = false
        tvFont2.isSelected = false
        tvFont3.isSelected = false
        tvFont4.isSelected = false
    }

    private var isDrawerOpen = false

    // 方法用于切换侧滑菜单的显示和隐藏
    private fun toggleDrawer() {
        if (isDrawerOpen) {
            closeDrawer()
        } else {
            openDrawer()
        }
    }

    // 打开侧滑菜单
    private fun openDrawer() {
        mBinding.includeMenu.root.apply {
            animate().translationX(0f).setDuration(500)
            visibility = View.VISIBLE
        }
        isDrawerOpen = true
    }

    // 关闭侧滑菜单
    private fun closeDrawer() {
        val drawerWidth = -mBinding.includeMenu.root.width.toFloat()
        mBinding.includeMenu.root.animate().translationX(drawerWidth)
        isDrawerOpen = false
    }
}