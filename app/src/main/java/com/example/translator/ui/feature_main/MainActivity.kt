package com.example.translator.ui.feature_main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.translator.core.bases.activity.BaseActivityWithVM
import com.example.translator.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivityWithVM<MainViewModel, ActivityMainBinding>() {
    override val viewModel: MainViewModel by viewModels()

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                binding.apply {
                    // Update UI based on state
                    if (state.isLoading) {
                        // Show loading indicator
                    }
                    state.error?.let {
                        // Show error message
                    }
                }
            }
        }
    }

    private fun setupClickListeners() {
        binding.apply {
            // Setup click listeners
        }
    }
}

