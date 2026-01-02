package com.example.translator.ui.common

sealed class CommonDialogEvent {
    data class ShowDialog(val title: String, val message: String) : CommonDialogEvent()
    object DismissDialog : CommonDialogEvent()
}

