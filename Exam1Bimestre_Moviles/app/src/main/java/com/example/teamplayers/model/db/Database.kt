package com.example.teamplayers.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.teamplayers.model.data.Team
import com.example.teamplayers.model.data.PLayingPlayers
import com.example.teamplayers.model.data.PlayersTeamMapping

/**
 * The Room database for this app
 */
@Database(entities = [PLayingPlayers::class, Team::class, PlayersTeamMapping::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playersDao(): PlayerDao
    abstract fun teamDao() : TeamDao
    abstract fun PlayersTeamMappingDao(): PlayersTeamMappingDao
}