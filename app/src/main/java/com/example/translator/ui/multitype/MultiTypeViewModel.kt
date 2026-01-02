package com.example.translator.ui.multitype

import androidx.lifecycle.viewModelScope
import com.example.translator.core.bases.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MultiTypeViewModel : BaseViewModel() {
    private val _items = MutableStateFlow<List<MultiTypeModel>>(emptyList())
    val items: StateFlow<List<MultiTypeModel>> = _items.asStateFlow()
}

