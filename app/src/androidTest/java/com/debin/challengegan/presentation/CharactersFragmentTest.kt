package com.debin.challengegan.presentation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.debin.challengegan.R
import com.debin.challengegan.presentation.characters.CharactersFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharactersFragmentTest {

    @Test
    fun testNavigation_launches_character_Fragment() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.characters_navigation)
        val senario = launchFragmentInContainer<CharactersFragment>()
        senario.onFragment {fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        Espresso.onView(withId(R.id.charactersFragment))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

}