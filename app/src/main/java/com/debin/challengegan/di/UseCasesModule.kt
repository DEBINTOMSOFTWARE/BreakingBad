package com.debin.challengegan.di

import com.challengegan.characters.domain.executor.JobExecutor
import com.debin.challengegan.characters.interactors.GetCharacters
import com.debin.challengegan.framework.executor.UiThread
import org.koin.dsl.module

val useCasesModule = module {
    factory { GetCharacters(get(), JobExecutor(), UiThread()) }
}