package com.example.translator.ui.multitype

import android.os.Bundle
import androidx.activity.viewModels
import com.example.translator.core.bases.activity.BaseActivityWithVM

class MultiTypeActivity : BaseActivityWithVM<MultiTypeViewModel, com.example.translator.databinding.ActivityMainBinding>() {
    override val viewModel: MultiTypeViewModel by viewModels()

    override fun getViewBinding(): com.example.translator.databinding.ActivityMainBinding {
        return com.example.translator.databinding.ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setup activity
    }
}

