package com.example.core.bases.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.core.bases.viewmodel.BaseViewModel

abstract class BaseActivityWithVM<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: VB
    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        initViews()
        observeData()
    }

    abstract fun getViewBinding(): VB
    abstract fun initViews()
    open fun observeData() {}
}

