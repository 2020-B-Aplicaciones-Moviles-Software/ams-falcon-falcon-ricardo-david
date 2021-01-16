package com.example.teamplayers.di.module

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.teamplayers.model.db.AppDatabase
import com.example.teamplayers.model.db.TeamDao
import com.example.teamplayers.model.db.PlayersTeamMappingDao
import com.example.teamplayers.model.db.PlayerDao
import com.example.teamplayers.utils.Constants
import com.example.teamplayers.utils.Utils
import com.example.teamplayers.viewmodel.TeamViewModelFactory
import com.example.teamplayers.viewmodel.PlayersViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * class annotated with @Module, which will receive the application instance via constructor,
 * store it in a property, and return it using a method annotated with @Provides @Singleton
 * App related instance provider.
 */
@Module
class AppModule(val app: Application) {

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Change table when version changes
            }
        }
    }

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideRecipesDatabase(app: Application): AppDatabase = Room.databaseBuilder(
        app,
        AppDatabase::class.java, Constants.DATABASE_NAME
    )
        /*.addMigrations(MIGRATION_1_2)*/
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providePlayersDao(
        database: AppDatabase
    ): PlayerDao = database. playersDao()

    @Provides
    @Singleton
    fun provideTeamViewModelFactory(
            factory: TeamViewModelFactory
    ): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideTeamDao(
            database: AppDatabase
    ): TeamDao = database.teamDao()

    @Provides
    @Singleton
    fun providePlayersViewModelFactory(
            factory: PlayersViewModelFactory
    ): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun providePlayersTeamMapping(
            database: AppDatabase
    ): PlayersTeamMappingDao = database.PlayersTeamMappingDao()

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)
}