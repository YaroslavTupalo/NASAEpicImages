package com.yaroslavtupalo.nasaepicimages.mvp.main

import com.yaroslavtupalo.nasaepicimages.dagger.ConfigPersistent
import com.yaroslavtupalo.nasaepicimages.data.DataManager
import com.yaroslavtupalo.nasaepicimages.mvp.base.BasePresenter
import com.yaroslavtupalo.nasaepicimages.util.rx_scheduler.SchedulerUtils
import javax.inject.Inject

/**
 * Created by Yaroslav on 01.02.2018.
 */
@ConfigPersistent
class MainPresenter @Inject constructor(private val dataManager: DataManager): BasePresenter<MainMvpView>(){

    fun getNASAImagesInfo(){
        checkViewAttached()
        mvpView?.showProgress(true)
        dataManager.getNASAEpicImages()
                .compose(SchedulerUtils.ioToMain())
                .subscribe({
                    result -> mvpView?.apply {
                    showProgress(false)
                    showNASAEpicImagesInfo(result)
                }
                }){
                    mvpView?.apply {
                        showProgress(false)
                        showError(it)
                    }
                }
    }
}