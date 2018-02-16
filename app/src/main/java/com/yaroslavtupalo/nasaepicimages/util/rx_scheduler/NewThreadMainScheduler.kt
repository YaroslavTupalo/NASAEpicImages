package com.yaroslavtupalo.nasaepicimages.util.rx_scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Yaroslav on 02.02.2018.
 */
class NewThreadMainScheduler<T> private constructor(): BaseScheduler<T>(Schedulers.newThread(), AndroidSchedulers.mainThread())