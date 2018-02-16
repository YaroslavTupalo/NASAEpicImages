package com.yaroslavtupalo.nasaepicimages.dagger.module

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Yaroslav on 01.02.2018.
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    internal fun provideActivity(): Activity = activity

    @Provides
    internal fun providesContext(): Context = activity
}