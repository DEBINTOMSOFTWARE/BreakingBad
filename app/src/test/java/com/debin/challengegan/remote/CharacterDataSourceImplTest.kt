package com.debin.challengegan


import com.debin.challengegan.characters.domain.CharacterResponseItem
import com.debin.challengegan.framework.network.ApiService
import com.debin.challengegan.framework.remote.CharacterDataSourceImpl
import com.debin.challengegan.framework.remote.utils.CharactersFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CharacterDataSourceImplTest {

    private lateinit var mockApiService: ApiService
    private lateinit var charactersDataSourceImpl: CharacterDataSourceImpl

    @Before
    fun setUp() {
        mockApiService = mock()
        charactersDataSourceImpl =
            CharacterDataSourceImpl(
                mockApiService
            )
    }

    @Test
    fun verifyGetCharactersAsyncCalled() {
        charactersDataSourceImpl.getCharactersAsync()
        verify(mockApiService).getCharacters()
    }

    @Test
    fun getCharactersRepositoryComplete_without_errors() {
        val characters = CharactersFactory.makeCharactersResponse()
        stubWheneverThenReturn(Single.just(characters))
        val testObserver = charactersDataSourceImpl.getCharactersAsync().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }

    @Test
    fun getCharactersRepositoryComplete_with_errors() {
        val error = CharactersFactory.makeResponseError()
        stubWheneverThenReturn(Single.error(error))
        val testObserver = charactersDataSourceImpl.getCharactersAsync().toObservable().test()
        testObserver.assertError(error)
    }

    @Test
    fun getCharactersRepository_returns_data() {
        val characters = CharactersFactory.makeCharactersResponse()
        stubWheneverThenReturn(Single.just(characters))
        val testObserver = charactersDataSourceImpl.getCharactersAsync().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
        testObserver.assertValue(characters)
    }

    private fun stubWheneverThenReturn(single: Single<List<CharacterResponseItem>>) {
        whenever(mockApiService.getCharacters()).thenReturn(single)
    }

}