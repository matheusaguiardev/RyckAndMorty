package com.raiuga.rickandmorty.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raiuga.domain.model.CharacterInfo
import com.raiuga.domain.model.CharacterList
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

    private val _characterList = MutableStateFlow(CharacterList())
    val characterList: StateFlow<CharacterList> = _characterList.asStateFlow()

    private val _characterFilteredList = MutableStateFlow(CharacterList())
    val characterFilteredList: StateFlow<CharacterList> = _characterFilteredList.asStateFlow()

    private fun getCharacterList() {
        viewModelScope.launch {
            with(charactersUseCase) {
                updateResultOnScreen(fetchSinglePageAndCache()) {
                    _characterList.value = _characterList.value
                        .copy(list = it.orEmpty())
                }

                fetchCharacterList(currentPage)
                    .flowOn(Dispatchers.IO)
                    .catch {
                        Log.e("Kawabunga", "Erro: ${it.message}")
                    }
                    .collect { outcome ->
                        updateResultOnScreen(outcome) {

                        }
                    }
            }
        }
    }

    fun filterCharactersBy(name: String, status: String) {
        viewModelScope.launch {
            with(characterFilteredUseCase) {
                fetchCharacter(name = name, status = status)
                    .flowOn(Dispatchers.IO)
                    .catch {
                        Log.e("Kawabunga", "Erro: ${it.message}")
                    }
                    .collect { outcome ->
                        updateResultOnScreen(outcome) {
                            _characterFilteredList.value = _characterFilteredList.value
                                .copy(list = it.orEmpty())
                        }
                    }
            }
        }
    }

    private fun updateResultOnScreen(
        outcome: Outcome<CharacterList>,
        onSuccess: (List<CharacterInfo>?) -> Unit
    ) {
        when (outcome) {
            is Outcome.Success -> onSuccess(outcome.data?.list)
            is Outcome.Error -> {
                Log.d(DEBUG_TAG, "Error: ${outcome.error?.message}")
            }
        }
    }

}