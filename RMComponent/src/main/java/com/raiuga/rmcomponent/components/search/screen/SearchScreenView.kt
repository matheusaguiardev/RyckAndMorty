package com.raiuga.rmcomponent.components.search.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raiuga.rmcomponent.components.header.HeaderScreenView
import com.raiuga.rmcomponent.components.loading.LoadingScreenView
import com.raiuga.rmcomponent.model.ErrorStateScreen
import com.raiuga.rmcomponent.model.LoadingStateScreen
import com.raiuga.rmcomponent.model.StateScreen
import com.raiuga.rmcomponent.model.SearchScreenItem
import com.raiuga.rmcomponent.model.SuccessStateScreen

@Composable
fun SearchScreenView(
    modifier: Modifier = Modifier,
    titleScreen: String,
    stateScreen: StateScreen,
    optionsList: List<SearchScreenItem> = emptyList(),
    onFindMoreClick: (String) -> Unit = {},
    onItemClick: (String) -> Unit
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
            stateScreen = stateScreen,
            onFindMoreClick = onFindMoreClick,
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
        stateScreen = SuccessStateScreen,
        titleScreen = "Title preview",
        optionsList = previewList
    ){}
}