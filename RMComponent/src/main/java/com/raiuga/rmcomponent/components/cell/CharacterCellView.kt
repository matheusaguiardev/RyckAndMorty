package com.raiuga.rmcomponent.components.cell

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CharacterCellView(
    id: Int,
    imageUrl: String,
    name: String,
    onClick: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .clickable { onClick(id) }
            .height(80.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            modifier = Modifier
                .fillMaxHeight(),
            alignment = Alignment.Center,
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = name,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Preview
@Composable
fun CharacterCellPreview() {
    val previewImage = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
    val previewName = "Rick"
    CharacterCellView(0, previewImage, previewName) {}
}