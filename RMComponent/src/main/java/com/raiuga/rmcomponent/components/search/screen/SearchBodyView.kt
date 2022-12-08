package com.raiuga.rmcomponent.components.search.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.raiuga.rmcomponent.components.cell.CharacterCellView
import com.raiuga.rmcomponent.components.search.SearchBarView
import com.raiuga.rmcomponent.constants.Paddings.DP_LARGE_SPACE
import com.raiuga.rmcomponent.constants.Paddings.DP_MEDIUM_SPACE
import com.raiuga.rmcomponent.extension.removeAccent
import com.raiuga.rmcomponent.model.SearchScreenItem

@OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBodyComponent(
    padding: PaddingValues,
    optionsList: List<SearchScreenItem> = emptyList(),
    onClick: (Int) -> Unit
) {
    var selectedText by remember { mutableStateOf("") }
    val filteredList by remember {
        derivedStateOf {
            optionsList.filter {
                it.name.removeAccent().contains(
                    other = selectedText,
                    ignoreCase = true
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(DP_LARGE_SPACE)
                .fillMaxWidth()
        ) {
            SearchBarView { selectedText = it }
        }

        if (filteredList.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(filteredList.size) { index ->
                    with(filteredList[index]) {
                        CharacterCellView(
                            id = id,
                            imageUrl = urlImage,
                            name = name,
                            onClick = onClick
                        )
                    }
                }
            }
        } else {
            Text(
                modifier = Modifier
                    .padding(vertical = DP_MEDIUM_SPACE)
                    .padding(start = DP_LARGE_SPACE),
                text = "Nenhum resultado encontrado",
                style = MaterialTheme.typography.caption
            )
        }
    }
}