package com.example.mockchat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(viewModel: ChatViewModel = hiltViewModel()) {
    Scaffold(bottomBar = { ChatInputBox(viewModel) }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn{
                items(viewModel.uiState){
                    Text(text = it, fontSize = 30.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChatInputBox(viewModel: ChatViewModel){
    val keyboardController = LocalSoftwareKeyboardController.current
    var input by remember{ mutableStateOf("")}
    BottomAppBar() {
        TextField(modifier = Modifier.fillMaxWidth(0.82f), value = input, onValueChange = {input = it})
        Button(onClick = {
            viewModel.addContent(input)
            keyboardController?.hide()
            input = ""
        }) {
            Text(text = "send")
        }
    }
    
}