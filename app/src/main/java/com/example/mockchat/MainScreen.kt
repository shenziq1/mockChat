package com.example.mockchat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mockchat.ui.theme.Purple200

@Composable
fun MainScreen(viewModel: ChatViewModel = hiltViewModel()) {
    Scaffold(bottomBar = { ChatInputBox(viewModel) }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                items(viewModel.uiState) {
                    when (it.fromMe) {
                        true -> {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(text = it.content, fontSize = 30.sp)
                            }
                        }
                        false -> {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(text = it.content, fontSize = 30.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChatInputBox(viewModel: ChatViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var input by remember { mutableStateOf("") }
    Surface() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(value = input, onValueChange = { input = it })
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
            FloatingActionButton(
                modifier = Modifier.size(46.dp),
                onClick = {
                    viewModel.addContent(input)
                    keyboardController?.hide()
                    input = ""
                }, shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "send")
            }
        }
    }


}