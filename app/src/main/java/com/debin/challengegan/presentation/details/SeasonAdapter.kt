package com.debin.challengegan.presentation.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.debin.challengegan.R
import kotlinx.android.synthetic.main.item_layout_seasons.view.*

class SeasonAdapter (private val seasonsAppearance: ArrayList<Int>) :
    RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder>() {

    fun updateSeasonsAppearance(newSeasonAppearance : List<Int>) {
        seasonsAppearance.clear()
        seasonsAppearance.addAll(newSeasonAppearance)
        notifyDataSetChanged()
    }

    inner class SeasonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindCharacter(season: Int) = with(itemView) {
            tv_season.text = "Season $season"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        return SeasonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout_seasons, parent, false)
        )
    }

    override fun getItemCount(): Int = seasonsAppearance.size

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val occupation = seasonsAppearance[position]
        holder.bindCharacter(occupation)
    }

}