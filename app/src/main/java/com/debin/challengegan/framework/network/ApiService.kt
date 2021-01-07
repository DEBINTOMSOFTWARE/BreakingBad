package com.debin.challengegan.framework.network

import com.debin.challengegan.characters.domain.CharacterResponseItem
import com.debin.challengegan.framework.utils.GET_CHARACTERS
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET(GET_CHARACTERS)
    fun getCharacters() : Single<List<CharacterResponseItem>>

}