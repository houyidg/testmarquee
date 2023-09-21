package com.wl.handdanmu

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
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
        // 设置为全屏
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(mBinding.root)
        closeDrawer()

        mBinding.tv.startMarquee()

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

            tvSpeed0.setupSpeedClick(mBinding)
            tvSpeed1.setupSpeedClick(mBinding)
            tvSpeed15.setupSpeedClick(mBinding)
            tvSpeed2.setupSpeedClick(mBinding)
            tvSpeed3.setupSpeedClick(mBinding)
            tvSpeed0.callOnClick()

            tvWhiteColor.setOnClickListener {
                mBinding.tv.setTextColor(resources.getColor(R.color.white))
            }

            tvOrangeColor.setOnClickListener {
                mBinding.tv.setTextColor(resources.getColor(R.color.orange))
            }

            tvRedColor.setOnClickListener {
                mBinding.tv.setTextColor(resources.getColor(R.color.red))
            }
            tvTeaColor.setOnClickListener {
                mBinding.tv.setTextColor(resources.getColor(R.color.teal))
            }

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
        }
    }

    private fun TextView.setupSpeedClick(mBinding: ActivityMainBinding) {
        setOnClickListener {
            mBinding.includeMenu.setupSpeedDefault()
            isSelected = true
            mBinding.tv.setScrollSpeed(text.toString().replace("x","").toFloat())
        }
    }
    private fun LayoutMenuBinding.setupSpeedDefault() {
        tvSpeed0.isSelected = false
        tvSpeed1.isSelected = false
        tvSpeed15.isSelected = false
        tvSpeed2.isSelected = false
        tvSpeed3.isSelected = false
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