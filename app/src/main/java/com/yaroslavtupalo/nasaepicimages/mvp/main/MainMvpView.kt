package com.yaroslavtupalo.nasaepicimages.mvp.main

import com.yaroslavtupalo.nasaepicimages.data.moshi.Example
import com.yaroslavtupalo.nasaepicimages.mvp.base.MvpView

/**
 * Created by Yaroslav on 02.02.2018.
 */
interface MainMvpView: MvpView {

    fun showNASAEpicImagesInfo(result: List<Example>)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)
}