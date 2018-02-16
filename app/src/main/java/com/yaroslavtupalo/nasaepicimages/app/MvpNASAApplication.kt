package com.yaroslavtupalo.nasaepicimages.app

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.singhajit.sherlock.core.Sherlock
import com.squareup.leakcanary.LeakCanary
import com.tspoon.traceur.Traceur
import com.yaroslavtupalo.nasaepicimages.BuildConfig
import com.yaroslavtupalo.nasaepicimages.dagger.component.AppComponent
import com.yaroslavtupalo.nasaepicimages.dagger.component.DaggerAppComponent
import com.yaroslavtupalo.nasaepicimages.dagger.module.AppModule
import com.yaroslavtupalo.nasaepicimages.dagger.module.NetworkModule
import timber.log.Timber

/**
 * Created by Yaroslav on 01.02.2018.
 */
class MvpNASAApplication : MultiDexApplication() {

    private var appComponent: AppComponent? = null

    companion object {
        operator fun get(context: Context): MvpNASAApplication = context.applicationContext as MvpNASAApplication
    }

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
            LeakCanary.install(this)
            Sherlock.init(this)
            Traceur.enableLogging()
        }
    }

    // Needed to replace the component with a test specific one
    var component: AppComponent
    get() {
        if(appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(AppModule(this))
                    .networkModule(NetworkModule(this))
                    .build()
        }
        return appComponent as AppComponent
    }
    set(appComponent) {
        this.appComponent = appComponent
    }
}