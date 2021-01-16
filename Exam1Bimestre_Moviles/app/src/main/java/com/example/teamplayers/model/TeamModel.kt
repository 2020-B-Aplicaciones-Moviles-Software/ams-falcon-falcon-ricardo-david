package com.example.teamplayers.model

import androidx.lifecycle.LiveData
import com.example.teamplayers.model.data.Team
import com.example.teamplayers.model.data.PLayingPlayers
import com.example.teamplayers.model.data.PlayersTeamMapping
import com.example.teamplayers.model.db.TeamDao
import com.example.teamplayers.model.db.PlayersTeamMappingDao
import com.example.teamplayers.model.db.PlayerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 */
class TeamModel @Inject constructor(
        private val playerDao: PlayerDao,
        private val teamDao: TeamDao,
        private var playersTeamMappingDao: PlayersTeamMappingDao) {

    /**
     * Obtener los equipos de la BD
     */
    suspend fun getTeamFromDb(): LiveData<List<Team>> {
        return withContext(Dispatchers.IO) { teamDao.getTeams() }
    }

    suspend fun addTeamToDB(team: Team) {
        withContext(Dispatchers.IO) { teamDao.insert(team) }
    }

    suspend fun getPlayersTeamMappingForTeamId(teamId: Int): LiveData<List<PlayersTeamMapping>> {
        return withContext(Dispatchers.IO) {
            playersTeamMappingDao.getPlayersTeamMappingForTeamId(teamId)
        }
    }

    fun getPlayersWithListOfPlayersIds(idList: List<Int>): LiveData<List<PLayingPlayers>> {
        return playerDao.getPlayersWithListOfPlayersIds(idList)
    }

    suspend fun deleteTeamFromDB(team: Team): Int {
        return withContext(Dispatchers.IO) {
            //All recipes belongs to this category should be default category.
            playersTeamMappingDao.updateMappingForTeamId(team.id!!)
            teamDao.delete(team)
        }
    }

    suspend fun updateTeamToDB(categoryId: Int, newCategoryName: String) {
        withContext(Dispatchers.IO) {
            teamDao.update(categoryId, newCategoryName)
        }
    }
}