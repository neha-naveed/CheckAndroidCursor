package com.example.translator.ui.common

import androidx.lifecycle.viewModelScope
import com.example.translator.core.bases.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommonDialogViewModel : BaseViewModel() {
    private val _state = MutableStateFlow(CommonDialogState())
    val state: StateFlow<CommonDialogState> = _state.asStateFlow()

    fun handleEvent(event: CommonDialogEvent) {
        when (event) {
            is CommonDialogEvent.ShowDialog -> {
                _state.value = CommonDialogState(
                    isVisible = true,
                    title = event.title,
                    message = event.message
                )
            }
            is CommonDialogEvent.DismissDialog -> {
                _state.value = CommonDialogState(isVisible = false)
            }
        }
    }
}

