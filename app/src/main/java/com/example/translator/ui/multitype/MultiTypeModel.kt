package com.example.translator.ui.multitype

sealed class MultiTypeModel(val type: Int) {
    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }

    data class HeaderItem(val title: String) : MultiTypeModel(TYPE_HEADER)
    data class ContentItem(val data: Any) : MultiTypeModel(TYPE_ITEM)
}

