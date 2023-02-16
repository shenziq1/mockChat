package com.example.mockchat

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class MessageUiState(
    val content: String = "",
    val fromMe: Boolean = true
)

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {
    var uiState = mutableStateListOf(MessageUiState())

    init {
        initOthers()
    }

    private fun initOthers() {
        val others = listOf(
            MessageUiState("other", false),
            MessageUiState("message", false),
            MessageUiState("messageaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", false)
        )
        uiState.clear()
        others.forEach {
            uiState.add(it)
        }
    }

    fun addContent(content: String) {
        val message = MessageUiState(content)
        uiState.add(message)
    }
}