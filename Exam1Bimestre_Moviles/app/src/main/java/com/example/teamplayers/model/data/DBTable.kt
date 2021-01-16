package com.example.teamplayers.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "players", indices = [Index(value = ["playerLink"], unique = true)])
data class PLayingPlayers(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int? = null,
        @ColumnInfo(name = "playerName")
        var playerName: String? = null,
        @ColumnInfo(name = "playerLink")
        val playerLink: String? = null,
        @ColumnInfo(name = "playerDescription")
        var playerDescription: String? = null
) : Serializable


@Entity(tableName = "team", indices = [Index(value = ["teamName"], unique = true)])
data class Team(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int? = null,
        @ColumnInfo(name = "teamName")
        val teamName: String
) : Serializable {
    override fun toString(): String {
        return teamName.toUpperCase()
    }
}


@Entity(tableName = "players_team", primaryKeys = ["playersId", "teamId"])
data class PlayersTeamMapping(
        @ColumnInfo(name = "playersId")
        val playerId: Int,
        @ColumnInfo(name = "teamId")
        val teamId: Int
)

