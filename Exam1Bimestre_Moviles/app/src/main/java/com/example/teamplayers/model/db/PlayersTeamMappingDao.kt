package com.example.teamplayers.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teamplayers.model.data.PlayersTeamMapping

@Dao
interface PlayersTeamMappingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayersTeamMapping(playersTeamMapping: PlayersTeamMapping): Long

    @Query("SELECT * FROM players_team WHERE teamId=:categoryId")
    fun getPlayersTeamMappingForTeamId(categoryId: Int): LiveData<List<PlayersTeamMapping>>

    @Query("SELECT * FROM players_team WHERE playersId=:recipesId")
    fun getPlayersTeamMappingForPlayersId(recipesId: Int): PlayersTeamMapping

    @Query("DELETE FROM players_team WHERE playersId=:recipesId")
    fun deleteMappingForPlayersId(recipesId: Int): Int

    @Query("UPDATE players_team SET teamId=:categoryId WHERE playersId=:recipesId")
    fun updateMappingForPlayersId(recipesId: Int, categoryId: Int): Int

    @Query("UPDATE players_team SET teamId=0 WHERE teamId=:id")
    fun updateMappingForTeamId(id: Int)

    @Query("DELETE FROM players_team")
    fun deleteAllMappingData()
}
