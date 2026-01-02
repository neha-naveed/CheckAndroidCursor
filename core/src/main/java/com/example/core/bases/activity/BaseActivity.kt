package com.example.core.bases.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initViews()
        observeData()
    }

    abstract fun getLayoutResId(): Int
    abstract fun initViews()
    open fun observeData() {}
}

