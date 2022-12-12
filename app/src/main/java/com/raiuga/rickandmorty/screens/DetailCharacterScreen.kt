package com.raiuga.rickandmorty.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.raiuga.rickandmorty.constants.KeyScreens.CHARACTER_DETAIL_SCREEN
import com.raiuga.rickandmorty.viewmodel.CharacterViewModel

@Composable
fun DetailCharacterScreen(
    viewModel: CharacterViewModel
) {
    val character = viewModel.lastCharacterViewed

    Column(
        modifier = Modifier
            .testTag(CHARACTER_DETAIL_SCREEN)
    ) {
        character?.image?.let {
            AsyncImage(
                model = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                alignment = Alignment.Center,
                contentDescription = null
            )
        }

        character?.let {
            with(it) {
                Text(builderTexts("Nome: ", name))
                Text(builderTexts("Estado: ", status))
                Text(builderTexts("Espécie: ", species))
                Text(builderTexts("Tipo: ", type))
                Text(builderTexts("genero: ", gender))
                Text(builderTexts("Origem: ", origin.name))
                Text(builderTexts("Localização: ", location.name))
            }
        }
    }
}

fun builderTexts(attribute: String, value: String) = buildAnnotatedString {
    withStyle(style = SpanStyle(
        fontWeight = FontWeight.Bold,
        color = Color.Green)
    ) {
        append(attribute)
    }
    if(value.isNotEmpty()) {
        append(value)
    } else {
        append("-")
    }
}

@Preview
@Composable
fun DetailCharacterScreenPreview() {
//    DetailCharacterScreen()
}
