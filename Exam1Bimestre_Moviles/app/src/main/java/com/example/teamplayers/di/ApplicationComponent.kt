package com.example.teamplayers.di

import com.example.teamplayers.App
import com.example.teamplayers.di.module.AppModule
import com.example.teamplayers.di.module.BuildersViewModule
import com.example.teamplayers.di.module.RestApiModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Component, specifies who is going to be able to manually inject it:
 */
@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        BuildersViewModule::class, AppModule::class, RestApiModule::class]
)

interface ApplicationComponent {
    fun inject(app: App)
}