package com.raiuga.rickandmorty.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raiuga.domain.model.CharacterInfo
import com.raiuga.domain.model.Outcome
import com.raiuga.domain.repository.CharacterFilteredUseCase
import com.raiuga.domain.repository.CharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

private const val DEBUG_TAG = "CharacterViewModel"

class CharacterViewModel(
    private val charactersUseCase: CharactersUseCase,
    private val characterFilteredUseCase: CharacterFilteredUseCase
) : ViewModel() {

    init {
        getCharacterList()
        filterCharactersBy(name = "Rick", status = "Alive")
    }

    private val _characterList = MutableStateFlow(emptyList<CharacterInfo>())
    val characterList: StateFlow<List<CharacterInfo>> = _characterList.asStateFlow()

    private val _characterFilteredList = MutableStateFlow(emptyList<CharacterInfo>())
    val characterFilteredList: StateFlow<List<CharacterInfo>> = _characterFilteredList.asStateFlow()

    fun getNextPage() {
        getCharacterList(++charactersUseCase.currentPage)
    }

    fun filterCharactersBy(name: String, status: String) {
        viewModelScope.launch {
            with(characterFilteredUseCase) {
                fetchCharacter(name = name, status = status)
                    .flowOn(Dispatchers.IO)
                    .catch {
                        Log.e(DEBUG_TAG, "Erro: ${it.message}")
                    }
                    .collect { outcome ->
                        updateResultOnScreen(outcome) {
                            _characterFilteredList.value = _characterFilteredList
                                .value + it

                        }
                    }
            }
        }
    }

    private fun getCharacterList(page: Int = charactersUseCase.currentPage) {
        viewModelScope.launch {
            with(charactersUseCase) {
                fetchCharacterList(page)
                    .flowOn(Dispatchers.IO)
                    .catch {
                        Log.e(DEBUG_TAG, "Erro: ${it.message}")
                    }
                    .collect { outcome ->
                        updateResultOnScreen(outcome) {
                            _characterList.value = _characterList.value + it
                        }
                    }
            }
        }
    }

    private fun updateResultOnScreen(
        outcome: Outcome<List<CharacterInfo>>,
        onSuccess: (List<CharacterInfo>) -> Unit
    ) {
        when (outcome) {
            is Outcome.Success -> onSuccess(outcome.data ?: emptyList())
            is Outcome.Error -> {
                Log.d(DEBUG_TAG, "Error: ${outcome.error?.message}")
            }
        }
    }

}