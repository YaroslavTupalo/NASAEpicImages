package com.yaroslavtupalo.nasaepicimages.dagger.component

import com.yaroslavtupalo.nasaepicimages.MainActivity
import com.yaroslavtupalo.nasaepicimages.dagger.PerActivity
import com.yaroslavtupalo.nasaepicimages.dagger.module.ActivityModule
import com.yaroslavtupalo.nasaepicimages.mvp.base.BaseActivity
import com.yaroslavtupalo.nasaepicimages.mvp.detail.DetailActivity
import dagger.Subcomponent

/**
 * Created by Yaroslav on 01.02.2018.
 */
@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(baseActivity: BaseActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(detailActivity: DetailActivity)
}