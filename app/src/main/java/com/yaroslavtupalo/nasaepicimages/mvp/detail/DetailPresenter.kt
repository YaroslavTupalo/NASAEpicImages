package com.yaroslavtupalo.nasaepicimages.mvp.detail

import com.yaroslavtupalo.nasaepicimages.dagger.ConfigPersistent
import com.yaroslavtupalo.nasaepicimages.data.DataManager
import com.yaroslavtupalo.nasaepicimages.mvp.base.BasePresenter
import com.yaroslavtupalo.nasaepicimages.util.rx_scheduler.SchedulerUtils
import javax.inject.Inject

/**
 * Created by Yaroslav on 15.02.2018.
 */
@ConfigPersistent
class DetailPresenter @Inject constructor(private val dataManager: DataManager): BasePresenter<DetailMvpView>(){

    fun getNASAEpicImageDetail(position: Int){
        checkViewAttached()
        mvpView?.showProgressBar(true)
        dataManager.getNASAEpicImages()
                .compose(SchedulerUtils.ioToMain())
                .subscribe({
                    mvpView?.apply {
                    showProgressBar(false)
                    showImage(it[position])
                }
                }){
                    mvpView?.apply {
                        showProgressBar(false)
                        showError(it)
                    }
                }
    }
}