package com.yaroslavtupalo.nasaepicimages.mvp.detail

import com.yaroslavtupalo.nasaepicimages.data.moshi.Example
import com.yaroslavtupalo.nasaepicimages.mvp.base.MvpView

/**
 * Created by Yaroslav on 15.02.2018.
 */
interface DetailMvpView: MvpView {

    fun showImage(image: Example)

    fun showProgressBar(show: Boolean)

    fun showError(error: Throwable)
}