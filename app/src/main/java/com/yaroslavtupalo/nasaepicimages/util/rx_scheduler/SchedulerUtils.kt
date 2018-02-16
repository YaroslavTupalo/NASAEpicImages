package com.yaroslavtupalo.nasaepicimages.util.rx_scheduler

/**
 * Created by Yaroslav on 02.02.2018.
 */
object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> = IoMainScheduler()
}