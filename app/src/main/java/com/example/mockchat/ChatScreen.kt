package com.example.mockchat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mockchat.shared.Avatar

@Composable
fun ChatScreen(viewModel: ChatViewModel = hiltViewModel()) {
    Scaffold(
        topBar = { ChatTopBar() },
        bottomBar = { ChatInputBox(viewModel) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                items(viewModel.uiState) {
                    when (it.fromMe) {
                        false -> {
                            Column() {
                                Spacer(modifier = Modifier.height(5.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Avatar(R.drawable.mock_other_avatar)
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Card(
                                        modifier = Modifier.widthIn(0.dp, 320.dp).heightIn(50.dp),
                                        shape = RoundedCornerShape(16.dp)
                                    ) {
                                        Row() {
                                            Spacer(modifier = Modifier.width(5.dp))
                                            Text(text = it.content, fontSize = 30.sp)
                                            Spacer(modifier = Modifier.width(5.dp))
                                        }
                                    }
                                }
                            }
                        }
                        true -> {
                            Column() {
                                Spacer(modifier = Modifier.height(5.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Card(
                                        modifier = Modifier.widthIn(0.dp, 320.dp).heightIn(50.dp),
                                        shape = RoundedCornerShape(16.dp)
                                    ) {
                                        Row() {
                                            Spacer(modifier = Modifier.width(5.dp))
                                            Text(text = it.content, fontSize = 30.sp)
                                            Spacer(modifier = Modifier.width(5.dp))
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Avatar(R.drawable.mock_me_avatar)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = "name", fontSize = 24.sp) },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colors.surface)
    )
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

//@Preview
//@Composable
//fun Test(){
//    Spacer(modifier = Modifier.height(5.dp))
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.End,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Card(
//            modifier = Modifier.widthIn(90.dp, 330.dp),
////                .fillMaxWidth(0.8f)
////                .wrapContentWidth(),
//            shape = RoundedCornerShape(16.dp)
//        ) {
//            Row() {
//                Spacer(modifier = Modifier.width(5.dp))
//                Text(text = "hhaaaaaaaaaaaaaaaaaaaaaaaa", fontSize = 30.sp)
//                Spacer(modifier = Modifier.width(5.dp))
//            }
//        }
//        Spacer(modifier = Modifier.width(5.dp))
//        Avatar(R.drawable.mock_me_avatar)
//    }
//}