package com.debin.challengegan.presentation.characters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.debin.challengegan.R
import com.debin.challengegan.characters.domain.CharacterResponseItem
import com.debin.challengegan.framework.utils.getProgressDrawable
import com.debin.challengegan.framework.utils.loadImage
import kotlinx.android.synthetic.main.item_layout_characters.view.*
import java.util.*
import kotlin.collections.ArrayList

private const val TAG = "CharactersAdapter"
class CharactersAdapter(private var characters: ArrayList<CharacterResponseItem>,
                        private val clickListener: OnCharacterItemClick) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>(), Filterable {

    var charactersFilterList = ArrayList<CharacterResponseItem>()

    init {
        charactersFilterList = characters
    }

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

    override fun getItemCount(): Int = charactersFilterList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = charactersFilterList[position]
        holder.bindCharacter(character)
        holder.itemView.setOnClickListener {
            clickListener.onClick(character)
        }
    }

    class OnCharacterItemClick(val clickListener : (character: CharacterResponseItem) -> Unit) {
        fun onClick(character: CharacterResponseItem) = clickListener(character)
    }


    fun seasonBasedFilter(seasons : ArrayList<Int>?) {
        println("$TAG :: seasons :: $seasons")
        if(seasons?.isEmpty()!!) {
            charactersFilterList = characters
        } else {
            val resultList = ArrayList<CharacterResponseItem>()
            for(row in characters) {
                if(row.appearance!= null) {
                    if(row.appearance.any { it in seasons }) {
                        resultList.add(row)
                    }}
            }
            charactersFilterList = resultList
            notifyDataSetChanged()
        }
    }

    override fun getFilter(): Filter {
        return filter
    }

    private val filter : Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val charSearch = constraint.toString()
            if (charSearch.isEmpty()) {
                charactersFilterList = characters
            } else {
                val resultList = ArrayList<CharacterResponseItem>()
                val filteredList = characters.filter {
                    it.name.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))
                }
                resultList.addAll(filteredList)
                charactersFilterList = resultList
            }
            val filterResults = FilterResults()
            filterResults.values = charactersFilterList
            return filterResults
        }


        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            charactersFilterList = results?.values as ArrayList<CharacterResponseItem>
            notifyDataSetChanged()
        }
    }
}