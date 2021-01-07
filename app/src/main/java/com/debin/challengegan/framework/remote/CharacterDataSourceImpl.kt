package com.debin.challengegan.framework.remote

import com.debin.challengegan.characters.data.datasource.ICharactersDataSource
import com.debin.challengegan.characters.domain.CharacterResponseItem
import com.debin.challengegan.framework.network.ApiService
import io.reactivex.Single

class CharacterDataSourceImpl(private val apiService: ApiService) : ICharactersDataSource {
    override fun getCharactersAsync(): Single<List<CharacterResponseItem>> {
        return apiService.getCharacters()
    }
}