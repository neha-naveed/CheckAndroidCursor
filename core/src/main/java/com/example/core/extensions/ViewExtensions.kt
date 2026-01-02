package com.example.core.extensions

import android.view.View
import androidx.core.view.isVisible

fun View.setVisible(visible: Boolean) {
    isVisible = visible
}

fun View.setOnSingleClickListener(listener: View.OnClickListener) {
    setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        private val clickDelay: Long = 600

        override fun onClick(v: View) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime > clickDelay) {
                lastClickTime = currentTime
                listener.onClick(v)
            }
        }
    })
}

