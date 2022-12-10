package com.raiuga.rickandmorty.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.raiuga.rickandmorty.R
import com.raiuga.rickandmorty.constants.KeyScreens.CHARACTER_DETAIL_SCREEN
import com.raiuga.rickandmorty.viewmodel.CharacterViewModel
import com.raiuga.rmcomponent.components.cell.CharacterCellView
import com.raiuga.rmcomponent.components.header.HeaderScreenView
import com.raiuga.rmcomponent.components.search.screen.SearchScreenView
import com.raiuga.rmcomponent.model.SearchScreenItem

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: CharacterViewModel
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    val characters by viewModel.characterList.collectAsState()
    val stateScreen by viewModel.stateScree.collectAsState()

    fun navigateToDetail(charName: String) {
        with(viewModel) {
            lastCharacterViewed = findCharacterByName(charName)
            Log.d("kawabunga", lastCharacterViewed.toString())
        }
        navController.navigate(CHARACTER_DETAIL_SCREEN)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderScreenView(
            screenName = stringResource(id = R.string.app_name)
        ) { showDialog = true }

        LazyColumn {
            items(characters) { item ->
                CharacterCellView(
                    imageUrl = item.image,
                    name = item.name,
                    onClick = {
                        navigateToDetail(it)
                    }
                )
                if (characters.last() == item) {
                    viewModel.getNextPage()
                }
            }
        }

        if (showDialog) {
            Dialog(properties = DialogProperties(usePlatformDefaultWidth = false),
                onDismissRequest = { showDialog = false }) {
                SearchScreenView(
                    titleScreen = stringResource(id = R.string.filter),
                    stateScreen = stateScreen,
                    optionsList = characters.map {
                        SearchScreenItem(it.id, it.image, it.name)
                    },
                    onFindMoreClick = {
                        viewModel.filterCharactersBy(name = it)
                    },
                    onItemClick = {
                        navigateToDetail(it)
                    }
                )
            }
        }
    }
}