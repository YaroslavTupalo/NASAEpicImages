package com.yaroslavtupalo.nasaepicimages.data

import com.yaroslavtupalo.nasaepicimages.data.moshi.Example
import com.yaroslavtupalo.nasaepicimages.data.retrofit.NASAepicImageApi
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Yaroslav on 01.02.2018.
 */
@Singleton
class DataManager @Inject constructor(private val nasaAPI: NASAepicImageApi){

    fun getNASAEpicImages(): Single<List<Example>> =
            nasaAPI.getImages()
}