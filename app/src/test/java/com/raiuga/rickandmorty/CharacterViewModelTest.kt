package com.raiuga.rickandmorty

import com.raiuga.domain.model.Outcome
import com.raiuga.domain.repository.CharacterFilteredUseCase
import com.raiuga.domain.repository.CharactersUseCase
import com.raiuga.rickandmorty.mocks.CharacterInfoMock
import com.raiuga.rickandmorty.viewmodel.CharacterViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class CharacterViewModelTest {
    private val charactersUseCase: CharactersUseCase = mockk(relaxed = true)
    private val characterFilteredUseCase: CharacterFilteredUseCase = mockk()
    private val viewModel by lazy {
        CharacterViewModel(
            charactersUseCase,
            characterFilteredUseCase
        )
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test fun `get first characters`() = runBlocking {
        `given that request to get the character is success`()
        `when the screen starts`()
        `then the success result should be the list`()
    }

    @Test fun `find a character by name`() = runBlocking {
        `given that request to filter a character is success`()
        `when user search for character by name`()
        `then the result should be the character expected`()
    }

    // ------------------ Given ------------------

    private fun `given that request to get the character is success`() {
        coEvery { charactersUseCase.fetchCharacterList(0) } returns flow {
            emit(Outcome.Success(listOf(CharacterInfoMock)))
        }
    }

    private fun `given that request to filter a character is success`() {
        coEvery { charactersUseCase.fetchCharacterList(0) } returns flow {
            emit(Outcome.Success(listOf(CharacterInfoMock)))
        }

        coEvery { characterFilteredUseCase.fetchCharacter(
            CharacterInfoMock.name,
            CharacterInfoMock.status
        ) } returns flow {
            emit(Outcome.Success(listOf(CharacterInfoMock)))
        }
    }

    // ------------------ When ------------------

    private fun `when the screen starts`() {
        viewModel
    }

    private fun `when user search for character by name`() {
        viewModel.filterCharactersBy(
            CharacterInfoMock.name,
            CharacterInfoMock.status
        )
    }

    // ------------------ Then ------------------

    private suspend fun `then the success result should be the list`() {
        Assert.assertEquals(
            listOf(CharacterInfoMock),
            viewModel.characterList.first()
        )
    }

    private suspend fun `then the result should be the character expected`() {
        Assert.assertEquals(
            listOf(CharacterInfoMock),
            viewModel.characterList.first()
        )
    }

}