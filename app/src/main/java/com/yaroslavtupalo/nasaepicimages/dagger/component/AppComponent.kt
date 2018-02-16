package com.yaroslavtupalo.nasaepicimages.dagger.component

import android.app.Application
import android.content.Context
import com.yaroslavtupalo.nasaepicimages.dagger.ApplicationContext
import com.yaroslavtupalo.nasaepicimages.dagger.module.AppModule
import com.yaroslavtupalo.nasaepicimages.data.DataManager
import com.yaroslavtupalo.nasaepicimages.data.retrofit.NASAepicImageApi
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Yaroslav on 01.02.2018.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager

    fun nasaApi(): NASAepicImageApi
}