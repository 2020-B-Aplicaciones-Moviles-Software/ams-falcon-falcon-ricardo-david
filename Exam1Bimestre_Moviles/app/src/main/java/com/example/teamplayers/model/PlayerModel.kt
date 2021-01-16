package com.example.teamplayers.model

import androidx.lifecycle.LiveData
import com.example.teamplayers.model.data.Team
import com.example.teamplayers.model.data.PLayingPlayers
import com.example.teamplayers.model.data.PlayersTeamMapping
import com.example.teamplayers.model.db.TeamDao
import com.example.teamplayers.model.db.PlayersTeamMappingDao
import com.example.teamplayers.model.db.PlayerDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject


class PlayerModel @Inject constructor(
        private val playerDao: PlayerDao,
        private val playersTeamMappingDao: PlayersTeamMappingDao,
        private val teamDao: TeamDao) {


    suspend fun getPlayersFromDb(): LiveData<List<PLayingPlayers>> {
        return withContext(IO) { playerDao.getPlayers() }
    }

    /**
     * BOORRAR JUGADORES DE LA BD
     */
    suspend fun deletePlayers(PLayingPlayers: PLayingPlayers): Int {
        return withContext(IO) {
            playerDao.delete(PLayingPlayers)
        }
    }

    /**
     * ACTUALIZAR JUGADORES DE LA BD
     */
    suspend fun updatePlayers(PLayingPlayers: PLayingPlayers): Int {
        return withContext(IO) {
            playerDao.update(PLayingPlayers)
        }
    }

    suspend fun getPlayersDetails(id: Int): PLayingPlayers {
        return withContext(IO) { playerDao.queryPlayersDetails(id) }
    }

    suspend fun addPlayersData(PLayingPlayers: PLayingPlayers): Long {
        return withContext(IO) {
            playerDao.insertManual(PLayingPlayers)
        }
    }

    suspend fun deletesAllPlayers() {
        withContext(IO) {
            playerDao.deleteAll()
        }
    }

    suspend fun getTeamsFromDb(): LiveData<List<Team>> {
        return withContext(IO) { teamDao.getTeams() }
    }

    suspend fun inserPlayersTeamMapping(playersTeamMapping: PlayersTeamMapping): Long {
        return withContext(IO) {
            playersTeamMappingDao.insertPlayersTeamMapping(playersTeamMapping)
        }
    }

    suspend fun getPlayersTeamMappingForPlayersId(recipesId: Int): PlayersTeamMapping {
        return withContext(IO) { playersTeamMappingDao.getPlayersTeamMappingForPlayersId(recipesId) }
    }

    suspend fun deleteMappingPlayersId(recipesId: Int): Int {
        return withContext(IO) {
            playersTeamMappingDao.deleteMappingForPlayersId(recipesId)
        }
    }

    suspend fun updateMappingForPlayersId(recipesId: Int, categoryId: Int): Int {
        return withContext(IO) {
            playersTeamMappingDao.updateMappingForPlayersId(recipesId, categoryId)
        }
    }

    suspend fun deleteAllMappingData() {
        withContext(IO) {
            playersTeamMappingDao.deleteAllMappingData()
        }
    }
}