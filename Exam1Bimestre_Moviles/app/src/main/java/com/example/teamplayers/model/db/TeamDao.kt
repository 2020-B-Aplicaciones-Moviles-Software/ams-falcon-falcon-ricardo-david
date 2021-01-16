package com.example.teamplayers.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teamplayers.model.data.Team

@Dao
interface TeamDao {

    @Query("SELECT * FROM team")
    fun getTeams(): LiveData<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(team: Team)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(teamList: List<Team>)

    @Delete
    fun delete(team: Team): Int

    @Query("UPDATE team SET teamName = :newCategoryName WHERE id = :categoryId")
    fun update(categoryId: Int, newCategoryName: String)
}
