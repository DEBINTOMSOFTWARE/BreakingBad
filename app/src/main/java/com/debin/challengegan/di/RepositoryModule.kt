package com.debin.challengegan.di

import com.debin.challengegan.characters.data.repository.CharactersRepository
import com.debin.challengegan.characters.domain.repository.ICharactersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ICharactersRepository> { CharactersRepository(get()) }
}