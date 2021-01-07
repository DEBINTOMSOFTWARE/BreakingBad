package com.debin.challengegan.presentation.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.debin.challengegan.R
import com.debin.challengegan.characters.domain.CharacterResponseItem
import com.debin.challengegan.framework.utils.getProgressDrawable
import com.debin.challengegan.framework.utils.loadImage
import kotlinx.android.synthetic.main.item_layout_characters.view.*

class CharactersAdapter(private val characters: ArrayList<CharacterResponseItem>) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    fun updateCharacters(newCharacters: List<CharacterResponseItem>) {
        characters.clear()
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val progressDrawable = getProgressDrawable(view.context)
        fun bindCharacter(character: CharacterResponseItem) = with(itemView) {
            img_character.loadImage(character.img, progressDrawable)
            character_name.text = character.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout_characters, parent, false)
        )
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bindCharacter(character)

    }
}