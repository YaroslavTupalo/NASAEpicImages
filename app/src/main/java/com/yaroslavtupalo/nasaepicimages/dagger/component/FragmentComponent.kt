package com.yaroslavtupalo.nasaepicimages.dagger.component

import com.yaroslavtupalo.nasaepicimages.dagger.PerFragment
import com.yaroslavtupalo.nasaepicimages.dagger.module.FragmentModule
import dagger.Subcomponent

/**
 * Created by Yaroslav on 01.02.2018.
 */
@PerFragment
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent