package com.example.teamplayers.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.teamplayers.model.data.Team


class CategoriesPageAdapter(fragmentManager: FragmentManager, teamArray: ArrayList<Team>) : FragmentStatePagerAdapter(fragmentManager) {

    private var teamList: ArrayList<Team> = teamArray

    override fun getItem(position: Int): Fragment =
            PlayerssFragment.newInstance(position + 1, teamList[position].id!!)

    override fun getCount(): Int {
        return teamList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return teamList[position].teamName
    }

    fun getCurrentTeam(position: Int): Team {
        return teamList[position]
    }

    /**
     * ACTUALIZAR EL ADAPTADOR PARA LOS EQUIPOS
     */
    fun updateTeams(teamDataList: List<Team>) {
        teamList = ArrayList(teamDataList)
        notifyDataSetChanged()
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

}