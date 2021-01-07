package com.debin.challengegan.presentation.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.debin.challengegan.R
import kotlinx.android.synthetic.main.item_layout_seasons.view.*


class OccupationAdapter(private val occupations: ArrayList<String>) :
    RecyclerView.Adapter<OccupationAdapter.OccupationViewHolder>() {

    fun updateOccupation(newOccupation : List<String>) {
        occupations.clear()
        occupations.addAll(newOccupation)
        notifyDataSetChanged()
    }

    inner class OccupationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindCharacter(occupation: String) = with(itemView) {
           tv_season.text = occupation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OccupationViewHolder {
        return OccupationViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout_seasons, parent, false)
        )
    }

    override fun getItemCount(): Int = occupations.size

    override fun onBindViewHolder(holder: OccupationViewHolder, position: Int) {
        val occupation = occupations[position]
        holder.bindCharacter(occupation)

    }

}