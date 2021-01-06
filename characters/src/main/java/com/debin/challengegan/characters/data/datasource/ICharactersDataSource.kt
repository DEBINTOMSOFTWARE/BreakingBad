package com.debin.challengegan.characters.data.datasource

import com.debin.challengegan.characters.domain.CharacterResponseItem
import io.reactivex.Single

interface ICharactersDataSource {
    fun getCharactersAsync() : Single<List<CharacterResponseItem>>
}