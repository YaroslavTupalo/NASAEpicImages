package com.yaroslavtupalo.nasaepicimages.dagger.module

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.yaroslavtupalo.nasaepicimages.dagger.ActivityContext
import dagger.Module
import dagger.Provides

/**
 * Created by Yaroslav on 01.02.2018.
 */
@Module
class FragmentModule (private val fragment: Fragment) {

    @Provides
    internal fun providesFragment(): Fragment = fragment

    @Provides
    internal fun provideActivity(): FragmentActivity? = fragment.activity

    @Provides
    @ActivityContext
    internal fun providesContext(): Context? = fragment.context
}