package com.example.teamplayers

import android.app.Activity
import android.app.Application
import com.example.teamplayers.di.DaggerApplicationComponent
import com.example.teamplayers.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject


class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())

        DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
//            .restApiModule(RestApiModule(Constants.BASE_URL_REST_API))
            .build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}