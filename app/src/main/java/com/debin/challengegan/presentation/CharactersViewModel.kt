package com.debin.challengegan.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.debin.challengegan.characters.domain.CharacterResponseItem
import com.debin.challengegan.characters.interactors.GetCharacters
import com.debin.challengegan.framework.utils.Resource
import io.reactivex.observers.DisposableSingleObserver

private const val TAG = "CharactersViewModel"
class CharactersViewModel(private val getCharacters: GetCharacters) : ViewModel() {

    val loadingError = MutableLiveData<String>()
    val _characterList = MutableLiveData<Resource<List<CharacterResponseItem>>>()
    val characterList : LiveData<Resource<List<CharacterResponseItem>>>
    get() = _characterList

    init {
        loadingError.value = ""
        println("$TAG :: init called")
        getCharacters()
    }

    fun getCharacters() {
        getCharacters.execute(CharactersSubscriber())
    }

    inner class CharactersSubscriber : DisposableSingleObserver<List<CharacterResponseItem>>() {
        override fun onSuccess(characters: List<CharacterResponseItem>) {
            _characterList.value = Resource.Success(characters)
        }

        override fun onError(error: Throwable) {
            _characterList.value = Resource.Error(error.message)
             loadingError.value = error.message
        }
    }

    override fun onCleared() {
        super.onCleared()
        getCharacters.dispose()
    }
}