package com.debin.challengegan.framework.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.debin.challengegan.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_layout.*

const val TAG = "BottomSheetFragment"
class BottomSheetFragment(
    private val clickListener: OnFilter
) : BottomSheetDialogFragment() {

    private var fragmentView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.bottom_sheet_layout, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_ok.setOnClickListener {
            clickListener.onClick(seasonsSelected())
            dismiss()
        }
    }

    private fun seasonsSelected() : ArrayList<Int> {
        val  selectedSeasons:  ArrayList<Int> = arrayListOf()

           if(cb_season_one.isChecked)  {
               selectedSeasons.add(1)
            }
            if(cb_season_two.isChecked) {
                selectedSeasons.add(2)
            }
            if(cb_season_three.isChecked)  {
                selectedSeasons.add(3)
            }
            if(cb_season_four.isChecked)  {
                selectedSeasons.add(4)
            }
            if(cb_season_five.isChecked)  {
                selectedSeasons.add(5)
            }

        return selectedSeasons
    }

    class OnFilter(val clickListener : (seasonAppearance: ArrayList<Int>) -> Unit) {
        fun onClick(seasonAppearance: ArrayList<Int>) = clickListener(seasonAppearance)
    }

}