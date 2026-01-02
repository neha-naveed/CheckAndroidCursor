package com.example.translator.ui.feature_main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.translator.core.bases.activity.BaseActivityWithVM
import com.example.translator.core.extensions.hide
import com.example.translator.core.extensions.show
import com.example.translator.core.extensions.showToast
import com.example.translator.databinding.ActivityMainBinding
import com.example.translator.ui.feature_main.adapters.LanguageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivityWithVM<MainViewModel, ActivityMainBinding>() {
    override val viewModel: MainViewModel by viewModels()

    private var sourceLanguageAdapter: LanguageAdapter? = null
    private var targetLanguageAdapter: LanguageAdapter? = null
    private var isUpdatingLanguageSelection = false

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupLanguageSpinners()
        setupObservers()
        setupClickListeners()
        setupTextWatchers()
    }

    private fun setupLanguageSpinners() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if (state.availableLanguages.isNotEmpty()) {
                    setupLanguageAdapters(state.availableLanguages)
                }
            }
        }
    }

    private fun setupLanguageAdapters(languages: List<com.example.translator.domain.model.Language>) {
        sourceLanguageAdapter = LanguageAdapter(this, languages)
        targetLanguageAdapter = LanguageAdapter(this, languages)

        binding.sourceLanguageSpinner.apply {
            adapter = sourceLanguageAdapter
            setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (!isUpdatingLanguageSelection) {
                        sourceLanguageAdapter?.getLanguageCode(position)?.let { code ->
                            viewModel.handleEvent(
                                MainEvent.SourceLanguageSelected(code, position)
                            )
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            })
        }

        binding.targetLanguageSpinner.apply {
            adapter = targetLanguageAdapter
            setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (!isUpdatingLanguageSelection) {
                        targetLanguageAdapter?.getLanguageCode(position)?.let { code ->
                            viewModel.handleEvent(
                                MainEvent.TargetLanguageSelected(code, position)
                            )
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            })
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                updateUI(state)
            }
        }
    }

    private fun updateUI(state: MainState) {
        binding.apply {
            // Update source text
            if (sourceTextEditText.text?.toString() != state.sourceText) {
                sourceTextEditText.setText(state.sourceText)
            }

            // Update translated text
            translatedTextEditText.setText(state.translatedText)

            // Update language spinners
            updateLanguageSpinners(state)

            // Update loading state
            if (state.isLoading) {
                progressBar.show()
                translateButton.isEnabled = false
            } else {
                progressBar.hide()
                translateButton.isEnabled = true
            }

            // Update error message
            state.error?.let { error ->
                errorTextView.text = error
                errorTextView.show()
                showToast(error)
            } ?: run {
                errorTextView.hide()
            }
        }
    }

    private fun updateLanguageSpinners(state: MainState) {
        if (state.availableLanguages.isNotEmpty()) {
            isUpdatingLanguageSelection = true

            sourceLanguageAdapter?.let { adapter ->
                val sourcePosition = adapter.getPositionByCode(state.sourceLanguage)
                if (sourcePosition >= 0 && binding.sourceLanguageSpinner.selectedItemPosition != sourcePosition) {
                    binding.sourceLanguageSpinner.setSelection(sourcePosition)
                }
            }

            targetLanguageAdapter?.let { adapter ->
                val targetPosition = adapter.getPositionByCode(state.targetLanguage)
                if (targetPosition >= 0 && binding.targetLanguageSpinner.selectedItemPosition != targetPosition) {
                    binding.targetLanguageSpinner.setSelection(targetPosition)
                }
            }

            isUpdatingLanguageSelection = false
        }
    }

    private fun setupClickListeners() {
        binding.apply {
            translateButton.setOnClickListener {
                viewModel.handleEvent(MainEvent.Translate)
            }

            swapLanguagesButton.setOnClickListener {
                viewModel.handleEvent(MainEvent.SwapLanguages)
            }
        }
    }

    private fun setupTextWatchers() {
        binding.sourceTextEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.handleEvent(MainEvent.SourceTextChanged(s?.toString() ?: ""))
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun initViews() {
        // Additional view initialization if needed
    }
}

