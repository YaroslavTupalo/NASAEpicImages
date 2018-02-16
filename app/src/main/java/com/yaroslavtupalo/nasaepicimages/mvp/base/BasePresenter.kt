package com.yaroslavtupalo.nasaepicimages.mvp.base

import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * Created by Yaroslav on 01.02.2018.
 */
open class BasePresenter<T : MvpView> : Presenter<T> {

    var mvpView: T? = null
        private set
    private val compositeSubscription = CompositeSubscription()

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        mvpView = null
        if (!compositeSubscription.isUnsubscribed) {
            compositeSubscription.clear()
        }
    }

    private val isViewAttached: Boolean
        get() = mvpView != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    fun addSubscription(subs: Subscription) {
        compositeSubscription.add(subs)
    }

    private class MvpViewNotAttachedException internal constructor() : RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")

}