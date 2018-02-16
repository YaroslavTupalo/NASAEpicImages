package com.yaroslavtupalo.nasaepicimages.dagger.module

import com.yaroslavtupalo.nasaepicimages.data.retrofit.NASAepicImageApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Yaroslav on 01.02.2018.
 */
@Module(includes = arrayOf(NetworkModule::class))
class ApiModule {

    @Provides
    @Singleton
    internal fun provideEPICNASAAPPI(retrofit: Retrofit): NASAepicImageApi =
            retrofit.create(NASAepicImageApi::class.java)
}