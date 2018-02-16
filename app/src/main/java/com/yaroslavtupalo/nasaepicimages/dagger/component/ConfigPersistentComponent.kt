package com.yaroslavtupalo.nasaepicimages.dagger.component

import com.yaroslavtupalo.nasaepicimages.dagger.ConfigPersistent
import com.yaroslavtupalo.nasaepicimages.dagger.module.ActivityModule
import com.yaroslavtupalo.nasaepicimages.dagger.module.FragmentModule
import dagger.Component

/**
 * Created by Yaroslav on 01.02.2018.
 */
@ConfigPersistent
@Component(dependencies = [AppComponent::class])
interface ConfigPersistentComponent {

    fun activityComponent(activityModule: ActivityModule): ActivityComponent

    fun fragmentComponent(fragmentModule: FragmentModule): FragmentComponent
}