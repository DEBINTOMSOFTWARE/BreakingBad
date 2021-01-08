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
    val characterName = MutableLiveData<String>()
    val characterNickName = MutableLiveData<String>()
    val characterStatus = MutableLiveData<String>()
    private val _characterImage = MutableLiveData<String>()
    private val _characterList = MutableLiveData<Resource<List<CharacterResponseItem>>>()
    private val _characterOccupation = MutableLiveData<List<String>>()
    private val _seasonAppearance = MutableLiveData<List<Int>>()
    private val _backClick = MutableLiveData<Boolean>()
    private val _searchClick = MutableLiveData<Boolean>()
    private val _filterClick = MutableLiveData<Boolean>()
    val characterList : LiveData<Resource<List<CharacterResponseItem>>> get() = _characterList
    val characterOccupation : LiveData<List<String>> get() = _characterOccupation
    val seasonAppearance : LiveData<List<Int>> get() = _seasonAppearance
    val characterImage : LiveData<String> get() = _characterImage
    val backClick : LiveData<Boolean> get() = _backClick
    val searchClick : LiveData<Boolean> get() = _searchClick
    val filterClick : LiveData<Boolean> get() = _filterClick


    init {
        loadingError.value = ""
        characterName.value = ""
        characterNickName.value = ""
        characterStatus.value = ""
        _characterImage.value = ""
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
        characterName.value = characters.name
        characterNickName.value = characters.nickname
        characterStatus.value = characters.status
        _characterImage.value = characters.img
        _characterOccupation.value = characters.occupation
        _seasonAppearance.value = characters.appearance
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

    fun searchClicked() {
        _searchClick.value = false
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