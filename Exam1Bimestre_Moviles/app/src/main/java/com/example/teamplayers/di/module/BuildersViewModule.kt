package com.example.teamplayers.di.module

import com.example.teamplayers.views.AddPlayersActivity
import com.example.teamplayers.views.HomeActivity
import com.example.teamplayers.views.PlayersActivity
import com.example.teamplayers.views.PlayersDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * View realted module instance.
 */
@Module
abstract class BuildersViewModule {

    @ContributesAndroidInjector
    abstract fun contributeRecipesActivity(): PlayersActivity

    @ContributesAndroidInjector
    abstract fun contributeRecipesDetailsActivity(): PlayersDetailsActivity

    @ContributesAndroidInjector
    abstract fun contributeAddRecipesActivity(): AddPlayersActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity
}