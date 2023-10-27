package com.leboncointest.android.ui.UIEvent

sealed class UIEvent {
    data class ShowMessage(val message: String): UIEvent()
}
