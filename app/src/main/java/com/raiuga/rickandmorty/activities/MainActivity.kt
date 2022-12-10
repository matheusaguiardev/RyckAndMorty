package com.raiuga.rickandmorty.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raiuga.rickandmorty.constants.KeyScreens.CHARACTER_DETAIL_SCREEN
import com.raiuga.rickandmorty.constants.KeyScreens.CHARACTER_SCREEN
import com.raiuga.rickandmorty.screens.DetailCharacterScreen
import com.raiuga.rickandmorty.screens.HomeScreen
import com.raiuga.rickandmorty.ui.theme.RickAndMortyTheme
import com.raiuga.rickandmorty.viewmodel.CharacterViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val navController = rememberNavController()
    val viewModel = getViewModel<CharacterViewModel>()
    NavHost(navController = navController, startDestination = CHARACTER_SCREEN) {
        composable(CHARACTER_SCREEN) { HomeScreen(navController, viewModel) }
        composable(CHARACTER_DETAIL_SCREEN) {
            DetailCharacterScreen(
                viewModel = viewModel
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RickAndMortyTheme {
        Greeting()
    }
}