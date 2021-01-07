package com.debin.challengegan.di

import com.debin.challengegan.characters.data.datasource.ICharactersDataSource
import com.debin.challengegan.framework.remote.CharacterDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<ICharactersDataSource> { CharacterDataSourceImpl(get()) }
}