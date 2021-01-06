package com.debin.challengegan.characters.data.repository

import com.debin.challengegan.characters.data.datasource.ICharactersDataSource
import com.debin.challengegan.characters.domain.CharacterResponseItem
import com.debin.challengegan.characters.domain.repository.ICharactersRepository
import io.reactivex.Single

class CharactersRepository(private val dataSource : ICharactersDataSource) : ICharactersRepository {
    override fun getCharacters(): Single<List<CharacterResponseItem>> {
        return dataSource.getCharactersAsync()
    }
}