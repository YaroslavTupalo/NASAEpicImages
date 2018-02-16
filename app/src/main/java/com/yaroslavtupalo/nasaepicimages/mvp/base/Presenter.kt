package com.yaroslavtupalo.nasaepicimages.mvp.base

/**
 * Created by Yaroslav on 01.02.2018.
 */

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
interface Presenter<in V: MvpView> {

    fun attachView(mvpView: V)

    fun detachView()
}