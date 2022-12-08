package com.raiuga.rmcomponent.components.search.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raiuga.rmcomponent.components.header.HeaderScreenView
import com.raiuga.rmcomponent.model.SearchScreenItem

@Composable
fun SearchScreenView(
    modifier: Modifier = Modifier,
    titleScreen: String,
    optionsList: List<SearchScreenItem> = emptyList(),
    onItemClick: (Int) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            HeaderScreenView(
                screenName = titleScreen
            )
        }
    ) {
        SearchBodyComponent(
            padding = it,
            optionsList = optionsList,
            onClick = onItemClick
        )
    }
}


@Preview
@Composable
fun SearchScreenPreview() {
    val previewList = listOf(
        SearchScreenItem(0 , "https://rickandmortyapi.com/api/character/avatar/1.jpeg", "Rick"),
        SearchScreenItem(1 , "https://rickandmortyapi.com/api/character/avatar/2.jpeg", "Morty")
    )
    SearchScreenView(
        titleScreen = "Title preview",
        optionsList = previewList
    ){}
}