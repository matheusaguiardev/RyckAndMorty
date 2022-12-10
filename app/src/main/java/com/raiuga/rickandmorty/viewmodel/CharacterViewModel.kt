package com.raiuga.rickandmorty.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raiuga.domain.model.CharacterInfo
import com.raiuga.domain.model.Outcome
import com.raiuga.domain.repository.CharacterFilteredUseCase
import com.raiuga.domain.repository.CharactersUseCase
import com.raiuga.rmcomponent.model.ErrorStateScreen
import com.raiuga.rmcomponent.model.LoadingStateScreen
import com.raiuga.rmcomponent.model.StateScreen
import com.raiuga.rmcomponent.model.SuccessStateScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val DEBUG_TAG = "CharacterViewModel"

class CharacterViewModel(
    private val charactersUseCase: CharactersUseCase,
    private val characterFilteredUseCase: CharacterFilteredUseCase
) : ViewModel() {

    private val _characterList = MutableStateFlow(emptyList<CharacterInfo>())
    val characterList: StateFlow<List<CharacterInfo>> = _characterList.asStateFlow()

    private val _stateScreen: MutableStateFlow<StateScreen> = MutableStateFlow(SuccessStateScreen)
    val stateScree: StateFlow<StateScreen> = _stateScreen

    var lastCharacterViewed: CharacterInfo? = null

    init {
        getCharacterList()
    }

    fun getNextPage() {
        getCharacterList(++charactersUseCase.currentPage)
    }

    fun filterCharactersBy(
        name: String = "",
        status: String = ""
    ) {
        viewModelScope.launch {
            with(characterFilteredUseCase) {
                _stateScreen.value = LoadingStateScreen
                fetchCharacter(name = name, status = status)
                    .flowOn(Dispatchers.IO)
                    .catch {
                        Log.e(DEBUG_TAG, "Erro: ${it.message}")
                        _stateScreen.value = ErrorStateScreen
                    }
                    .collect { outcome ->
                        updateResultOnScreen(outcome) {
                            addCharacters(it)
                        }
                    }
            }
        }
    }

    fun findCharacterByName(name: String): CharacterInfo? {
        return _characterList.value.firstOrNull{ it.name == name }
    }

    private fun getCharacterList(page: Int = charactersUseCase.currentPage) {
        viewModelScope.launch {
            with(charactersUseCase) {
                fetchCharacterList(page)
                    .flowOn(Dispatchers.IO)
                    .catch {
                        Log.e(DEBUG_TAG, "Erro: ${it.message}")
                        _stateScreen.value = ErrorStateScreen
                    }
                    .collect { outcome ->
                        updateResultOnScreen(outcome) {
                            addCharacters(it)
                        }
                    }
            }
        }
    }

    private fun addCharacters(list: List<CharacterInfo>) {
        val charListTemporary = _characterList.value.toMutableList()
        list.forEach { character ->
            if(!charListTemporary.contains(character)) {
                charListTemporary.add(character)
            }
        }
        _characterList.value = charListTemporary
    }

    private fun updateResultOnScreen(
        outcome: Outcome<List<CharacterInfo>>,
        onSuccess: (List<CharacterInfo>) -> Unit
    ) {
        when (outcome) {
            is Outcome.Success -> {
                onSuccess(outcome.data ?: emptyList())
                _stateScreen.value = SuccessStateScreen
            }
            is Outcome.Error -> {
                _stateScreen.value = ErrorStateScreen
                Log.d(DEBUG_TAG, "Error: ${outcome.error?.message}")
            }
        }
    }

}