package com.debin.challengegan.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.debin.challengegan.characters.domain.CharacterResponseItem
import com.debin.challengegan.characters.interactors.GetCharacters
import com.debin.challengegan.framework.utils.Resource
import io.reactivex.observers.DisposableSingleObserver

private const val TAG = "CharactersViewModel"
class CharactersViewModel(private val getCharacters: GetCharacters) : ViewModel() {

    val loadingError = MutableLiveData<String>()
    val character = MutableLiveData<CharacterResponseItem>()
    private val _characterList = MutableLiveData<Resource<List<CharacterResponseItem>>>()
    private val _backClick = MutableLiveData<Boolean>()
    private val _searchClick = MutableLiveData<Boolean>()
    private val _filterClick = MutableLiveData<Boolean>()
    val characterList : LiveData<Resource<List<CharacterResponseItem>>> get() = _characterList
    val backClick : LiveData<Boolean> get() = _backClick
    val searchClick : LiveData<Boolean> get() = _searchClick
    val filterClick : LiveData<Boolean> get() = _filterClick


    init {
        loadingError.value = ""
        _backClick.value = false
        _searchClick.value = false
        _filterClick.value = false
        getCharacters()
    }

    fun getCharacters() {
        getCharacters.execute(CharactersSubscriber())
    }

    fun setCharacterDetails(characters : CharacterResponseItem) {
        saveCharacterToLive(characters)
    }

    private fun saveCharacterToLive(characters: CharacterResponseItem) {
        character.value = characters
    }

    fun filterSearch(newText : String) : LiveData<List<CharacterResponseItem>> {
      return Transformations.map(characterList) {
          when(it) {
              is Resource.Success -> {
                  it.result.filter {
                      it.name.toLowerCase().contains(newText.toLowerCase())
                  }
              }
              is Resource.Loading -> TODO()
              is Resource.Error -> TODO()
          }
      }
    }

    fun filterSeasonAppearence(seasons : ArrayList<Int>) : LiveData<List<CharacterResponseItem>> {
      return Transformations.map(characterList) {
          when(it) {
              is Resource.Success -> {
                  it.result.filter {
                      it?.appearance!=null
                  }.filter {
                      it.appearance.containsAll(seasons)
                  }
              }
              is Resource.Loading -> TODO()
              is Resource.Error -> TODO()
          }
      }
    }

    fun onBackClick() {
       _backClick.value = true
    }

    fun finishBackClick() {
        _backClick.value = false
    }

    fun onSearchIconClick() {
        _searchClick.value = true
    }

    fun filterClick() {
        _filterClick.value = true
    }

    fun filterClicked() {
        _filterClick.value = false
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