package com.example.teamplayers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.teamplayers.model.TeamModel
import com.example.teamplayers.model.data.Team
import com.example.teamplayers.model.data.PLayingPlayers
import com.example.teamplayers.model.data.PlayersTeamMapping
import javax.inject.Inject

class TeamViewModel @Inject constructor(private val teamModel: TeamModel) : ViewModel() {

    suspend fun loadTeams(): LiveData<List<Team>> {
        return teamModel.getTeamFromDb()
    }

    suspend fun addTeamToDB(team: Team) {
        teamModel.addTeamToDB(team)
    }

    suspend fun getPlayersTeamMappingForTeamId(categoryId: Int): LiveData<List<PlayersTeamMapping>> {
        return teamModel.getPlayersTeamMappingForTeamId(categoryId)
    }

    fun getPlayersWithListOfPlayersIds(idList: List<Int>): LiveData<List<PLayingPlayers>> {
        return teamModel.getPlayersWithListOfPlayersIds(idList)
    }

    suspend fun deleteTeamFromDB(team: Team): Int {
        return teamModel.deleteTeamFromDB(team)
    }

    suspend fun updateTeamToDB(categoryId: Int, newCategoryName: String) {
        teamModel.updateTeamToDB(categoryId, newCategoryName)
    }
}


