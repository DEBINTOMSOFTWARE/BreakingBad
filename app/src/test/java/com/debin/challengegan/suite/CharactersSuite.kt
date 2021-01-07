package com.debin.challengegan.suite

import com.debin.challengegan.CharacterDataSourceImplTest
import com.debin.challengegan.presentation.CharactersViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    CharacterDataSourceImplTest::class,
    CharactersViewModelTest::class
)
class CharactersSuite