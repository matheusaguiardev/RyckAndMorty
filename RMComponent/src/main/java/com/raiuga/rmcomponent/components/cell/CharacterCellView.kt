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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CharacterCellView(
    modifier: Modifier = Modifier,
    imageUrl: String,
    name: String,
    onClick: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .clickable { onClick(name) }
            .height(80.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            modifier = Modifier
                .fillMaxHeight(),
            alignment = Alignment.Center,
            contentDescription = null
        )

        val offset = Offset(5.0f, 10.0f)
        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = name,
            color = MaterialTheme.colors.onBackground,
            style = TextStyle(
                fontSize = 24.sp,
                shadow = Shadow(
                    color = MaterialTheme.colors.secondaryVariant,
                    offset = offset,
                    blurRadius = 3f
                ))
        )
    }
}

@Preview
@Composable
fun CharacterCellPreview() {
    val previewImage = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
    val previewName = "Rick"
    CharacterCellView(imageUrl = previewImage, name = previewName) {}
}