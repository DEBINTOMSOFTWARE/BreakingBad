package com.debin.challengegan.framework.remote.utils

import com.debin.challengegan.characters.domain.CharacterResponseItem

class CharactersFactory {

    companion object Factory {
       fun makeCharactersResponse() : List<CharacterResponseItem> {
            return characterResponseItem()
       }


       private fun characterResponseItem() : List<CharacterResponseItem> {
        val item1 = CharacterResponseItem(listOf(1,2,3), listOf(1,2,3), "09-07-1958", "Breaking Bad", 1,
                                          "https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg",
                                           "Walter White", "Heisenberg", listOf("High School Chemistry Teacher", "Meth King Pin"), "Bryan Cranston","Presumed dead")
        val item2 = CharacterResponseItem(listOf(1,2,3,4), listOf(1,2,3),
            "09-07-1958", "Breaking Bad", 2,
            "https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg",
            "Walter White", "Heisenberg", listOf("High School Chemistry Teacher", "Meth King Pin"), "Bryan Cranston","Presumed dead")
           return listOf(item1, item2)
       }

       fun makeResponseError() : Throwable {
           return Throwable()
       }
    }

}