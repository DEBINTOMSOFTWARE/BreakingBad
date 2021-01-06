package com.debin.challengegan.characters.data.repository

import com.debin.challengegan.characters.data.datasource.ICharactersDataSource
import com.debin.challengegan.characters.data.repository.CharactersRepository
import com.debin.challengegan.characters.data.utils.CharactersFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class CharactersRepositoryTest {

    private lateinit var mockDataSource: ICharactersDataSource
    private lateinit var charactersRepository: CharactersRepository

    @Before
    fun setUp() {
        mockDataSource = mock()
        charactersRepository = CharactersRepository(mockDataSource)
    }

    @Test
    fun verifyGetCharctersRepository_calls_getCharacterAsync() {
        charactersRepository.getCharacters()
        verify(mockDataSource).getCharactersAsync()
    }

    @Test
   fun getCharacterRepositoryComplete_without_errors() {
     val characters = CharactersFactory.makeCharactersResponse()
     Mockito.`when`(mockDataSource.getCharactersAsync()).thenReturn(Single.just(characters))
     val testObserver = charactersRepository.getCharacters().toObservable().test()
     testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }

    @Test
    fun getCharacterRepositoryComplete_with_errors() {
        val error = CharactersFactory.makeResponseError()
        Mockito.`when`(mockDataSource.getCharactersAsync()).thenReturn(Single.error(error))
        val testObserver = charactersRepository.getCharacters().toObservable().test()
        testObserver.assertError(error)
    }

    @Test
    fun getCharacterRepository_returns_data() {
        val characters = CharactersFactory.makeCharactersResponse()
        Mockito.`when`(mockDataSource.getCharactersAsync()).thenReturn(Single.just(characters))
        val testObserver = charactersRepository.getCharacters().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
        testObserver.assertValue(characters)
    }

}