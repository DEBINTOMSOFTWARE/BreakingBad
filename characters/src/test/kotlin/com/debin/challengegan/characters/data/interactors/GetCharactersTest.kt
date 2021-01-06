package com.debin.challengegan.characters.data.interactors

import com.challengegan.characters.domain.executor.PostExecutionThread
import com.challengegan.characters.domain.executor.ThreadExecutor
import com.debin.challengegan.characters.data.utils.CharactersFactory
import com.debin.challengegan.characters.domain.CharacterResponseItem
import com.debin.challengegan.characters.domain.repository.ICharactersRepository
import com.debin.challengegan.characters.interactors.GetCharacters
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetCharactersTest {
    private lateinit var getCharacters: GetCharacters
    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockGetCharacterInfoRespository : ICharactersRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockGetCharacterInfoRespository = mock()
        getCharacters = GetCharacters(mockGetCharacterInfoRespository, mockThreadExecutor, mockPostExecutionThread)
    }

    @Test
    fun verifyGetCharacters_use_case_call_getBreeds() {
        getCharacters.buildUseCaseObservable(null, null)
        verify(mockGetCharacterInfoRespository).getCharacters()
    }

    @Test
    fun checkGetCharacters_use_case_complete_without_errors() {
        stubWheneverThenReturn(Single.just(CharactersFactory.makeCharactersResponse()))
        val testObserver = getCharacters.buildUseCaseObservable(null, null).test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }

    @Test
    fun checkGetCharacters_use_case_complete_with_errors() {
        val error = CharactersFactory.makeResponseError()
        stubWheneverThenReturn(Single.error(error))
        val testObserver = getCharacters.buildUseCaseObservable(null, null).test()
        testObserver.assertError(error)


    }
    @Test
    fun checkGetCharacters_use_case_returns_data() {
        val characters = CharactersFactory.makeCharactersResponse()
        stubWheneverThenReturn(Single.just(characters))
        val testObserver = getCharacters.buildUseCaseObservable(null, null).test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
        testObserver.assertValue(characters)
    }

    @After
    fun tearDown() {
        getCharacters.dispose()
    }

    private fun stubWheneverThenReturn(single: Single<List<CharacterResponseItem>>) {
        whenever(mockGetCharacterInfoRespository.getCharacters()).thenReturn(single)
    }

}