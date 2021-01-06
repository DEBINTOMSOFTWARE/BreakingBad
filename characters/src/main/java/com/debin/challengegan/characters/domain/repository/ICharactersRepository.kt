package com.debin.challengegan.characters.domain.repository

import com.debin.challengegan.characters.domain.CharacterResponseItem
import io.reactivex.Single

interface ICharactersRepository {
 fun getCharacters() : Single<List<CharacterResponseItem>>
}