package com.yaroslavtupalo.nasaepicimages.dagger.module

import android.app.Application
import android.content.Context
import com.yaroslavtupalo.nasaepicimages.dagger.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by Yaroslav on 01.02.2018.
 */
@Module(includes = arrayOf(ApiModule::class))
class AppModule (private val application: Application){

    @Provides
    internal fun provideApplication(): Application = application

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context = application
}