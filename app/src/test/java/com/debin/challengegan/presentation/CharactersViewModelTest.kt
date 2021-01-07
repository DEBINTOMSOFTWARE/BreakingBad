package com.debin.challengegan.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import com.debin.challengegan.characters.domain.CharacterResponseItem
import com.debin.challengegan.characters.interactors.GetCharacters
import com.debin.challengegan.framework.remote.utils.CharactersFactory
import com.debin.challengegan.framework.utils.Resource
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.RuntimeException

@RunWith(JUnit4::class)
class CharactersViewModelTest {
    private lateinit var mockGetCharactersUseCases: GetCharacters
    private lateinit var charactersViewModel: CharactersViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockGetCharactersUseCases = mock()
        charactersViewModel = CharactersViewModel(mockGetCharactersUseCases)
    }

    @Test
    fun test_loading_is_emitted() {
        val character = CharactersFactory.makeCharactersResponse()
        stubWheneverThenReturn(Single.just(character))
        charactersViewModel.getCharacters()
        val mediatorLiveData = MediatorLiveData<Resource<List<CharacterResponseItem>>>()
        mediatorLiveData.addSource(charactersViewModel.characterList) { result ->
            Assert.assertTrue(result is Resource.Loading)
        }
    }

    @Test
    fun test_error_is_emitted() {
        stubWheneverThenReturn(Single.error(RuntimeException()))
        charactersViewModel.getCharacters()
        val mediatorLiveData = MediatorLiveData<Resource<List<CharacterResponseItem>>>()
        mediatorLiveData.addSource(charactersViewModel.characterList) { result ->
            Assert.assertTrue(result is Resource.Error)
        }
    }

    @Test
    fun test_data_is_emitted() {
        val breed = CharactersFactory.makeCharactersResponse()
        stubWheneverThenReturn(Single.just(breed))
        charactersViewModel.getCharacters()
        val mediatorLiveData = MediatorLiveData<Resource<List<CharacterResponseItem>>>()
        mediatorLiveData.addSource(charactersViewModel.characterList) { result ->
            Assert.assertTrue(result is Resource.Success)
        }
    }

    @Test
    fun test_data_emitted_is_DogBreed() {
        val breed = CharactersFactory.makeCharactersResponse()
        stubWheneverThenReturn(Single.just(breed))
        charactersViewModel.getCharacters()
        val mediatorLiveData = MediatorLiveData<Resource<List<CharacterResponseItem>>>()
        mediatorLiveData.addSource(charactersViewModel.characterList) { result ->
            when(result) {
                is Resource.Success ->{
                    val breedResult = result.result
                    Assert.assertEquals(breed, breedResult)
                }
            }
        }
    }

    private fun stubWheneverThenReturn(single: Single<List<CharacterResponseItem>>) {
        whenever(mockGetCharactersUseCases.buildUseCaseObservable(any(), any())).thenReturn(single)
    }
}