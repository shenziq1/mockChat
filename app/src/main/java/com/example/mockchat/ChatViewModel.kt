package com.example.mockchat

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(): ViewModel() {
    var uiState = mutableStateListOf("")

    fun addContent(content: String){
        uiState.add(content)
    }
}