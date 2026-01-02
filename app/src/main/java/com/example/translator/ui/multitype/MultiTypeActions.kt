package com.example.translator.ui.multitype

sealed class MultiTypeActions {
    data class ItemClicked(val item: MultiTypeModel) : MultiTypeActions()
    data class ItemLongClicked(val item: MultiTypeModel) : MultiTypeActions()
}

