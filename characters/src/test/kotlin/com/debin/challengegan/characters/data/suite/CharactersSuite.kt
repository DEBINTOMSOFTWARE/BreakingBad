package com.debin.challengegan.characters.data.suite

import com.debin.challengegan.characters.data.repository.CharactersRepositoryTest
import com.debin.challengegan.characters.interactors.GetCharacters
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    GetCharacters::class,
    CharactersRepositoryTest::class
)
class CharactersSuite