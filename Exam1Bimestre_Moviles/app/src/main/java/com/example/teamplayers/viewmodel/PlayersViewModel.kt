package com.example.teamplayers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.teamplayers.model.PlayerModel
import com.example.teamplayers.model.data.Team
import com.example.teamplayers.model.data.PLayingPlayers
import com.example.teamplayers.model.data.PlayersTeamMapping
import javax.inject.Inject

class PlayersViewModel @Inject constructor(private val playerModel: PlayerModel) : ViewModel() {

    suspend fun loadPlayers(): LiveData<List<PLayingPlayers>> {
        return playerModel.getPlayersFromDb()
    }

    suspend fun loadPlayersDetails(id: Int): PLayingPlayers {
        return playerModel.getPlayersDetails(id)
    }

    suspend fun deletePlayersData(PLayingPlayers: PLayingPlayers): Int {
        return playerModel.deletePlayers(PLayingPlayers)
    }

    suspend fun updatePlayersData(PLayingPlayers: PLayingPlayers): Int {
        return playerModel.updatePlayers(PLayingPlayers)
    }

    suspend fun addPlayersDetails(PLayingPlayers: PLayingPlayers): Long {
        return playerModel.addPlayersData(PLayingPlayers)
    }

    suspend fun deleteAllPlayersData() {
        playerModel.deletesAllPlayers()
    }

    suspend fun getTeamList(): LiveData<List<Team>> {
        return playerModel.getTeamsFromDb()
    }

    suspend fun insertPlayersTeamMapping(playersTeamMapping: PlayersTeamMapping): Long {
        return playerModel.inserPlayersTeamMapping(playersTeamMapping)
    }

    suspend fun getPlayersTeamMappingForPlayersId(recipesId: Int): PlayersTeamMapping {
        return playerModel.getPlayersTeamMappingForPlayersId(recipesId)
    }

    suspend fun deleteMappingForPlayersId(recipesId: Int): Int {
        return playerModel.deleteMappingPlayersId(recipesId)
    }

    suspend fun updateMappingForPlayersId(recipesId: Int, categoryId: Int): Int {
        return playerModel.updateMappingForPlayersId(recipesId, categoryId)
    }

    suspend fun deleteAllMappingData() {
        playerModel.deleteAllMappingData()
    }
}


