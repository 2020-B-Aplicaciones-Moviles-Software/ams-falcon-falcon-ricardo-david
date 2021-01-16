package com.example.teamplayers.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teamplayers.model.data.PLayingPlayers

@Dao
interface PlayerDao {

    @Query("SELECT * FROM players")
    fun getPlayers(): LiveData<List<PLayingPlayers>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipes: PLayingPlayers): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertManual(players: PLayingPlayers): Long

    @Delete
    fun delete(PLayingPlayers: PLayingPlayers): Int

    @Update
    fun update(PLayingPlayers: PLayingPlayers): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(playersList: List<PLayingPlayers>)

    @Query(value = "SELECT * FROM players WHERE id = :id")
    fun queryPlayersDetails(id: Int): PLayingPlayers

    @Query("SELECT * FROM players WHERE id IN (:idList)")
    fun getPlayersWithListOfPlayersIds(idList: List<Int>): LiveData<List<PLayingPlayers>>

    @Query("DELETE FROM players")
    fun deleteAll()
}
