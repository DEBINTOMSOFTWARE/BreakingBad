package com.debin.challengegan.di

import com.debin.challengegan.presentation.CharactersViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharactersViewModel(get()) }
}