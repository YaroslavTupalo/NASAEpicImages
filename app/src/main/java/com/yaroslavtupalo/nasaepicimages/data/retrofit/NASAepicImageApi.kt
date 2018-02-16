package com.yaroslavtupalo.nasaepicimages.data.retrofit

import com.yaroslavtupalo.nasaepicimages.Constants
import com.yaroslavtupalo.nasaepicimages.data.moshi.Example
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Yaroslav on 01.02.2018.
 */
interface NASAepicImageApi {

    @GET("/EPIC/api/natural/images")
    fun getImages (@Query("api_key") api_key: String = Constants.API_KEY): Single<List<Example>>
}