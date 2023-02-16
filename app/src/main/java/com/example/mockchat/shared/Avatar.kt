package com.example.mockchat.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mockchat.R

@Composable
fun Avatar(painterResourceId: Int) {
    Image(
        modifier = Modifier.clip(RoundedCornerShape(16.dp)).size(50.dp),
        painter = painterResource(id = painterResourceId),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}