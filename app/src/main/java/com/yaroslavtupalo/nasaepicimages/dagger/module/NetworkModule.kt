package com.yaroslavtupalo.nasaepicimages.dagger.module

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.yaroslavtupalo.nasaepicimages.BuildConfig
import com.yaroslavtupalo.nasaepicimages.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

/**
 * Created by Yaroslav on 01.02.2018.
 */
@Module
class NetworkModule(private val context: Context) {

    protected fun getBaseUrl() = Constants.API_URL

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
            Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .client(okHttpClient)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Provides
    @Singleton
    internal fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor,
                                     chuckInterceptor: ChuckInterceptor,
                                     stethoInterceptor: StethoInterceptor): OkHttpClient{
        val httpClientBuilder = OkHttpClient.Builder()
        if(BuildConfig.DEBUG){
            httpClientBuilder.addInterceptor(httpLoggingInterceptor)
            httpClientBuilder.addInterceptor(chuckInterceptor)
            httpClientBuilder.addInterceptor(stethoInterceptor)
        }
        return httpClientBuilder.build()
    }

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor{
                message -> Timber.d(message)
            }.setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    internal fun provideChuckInterceptor(): ChuckInterceptor = ChuckInterceptor(context)

    @Provides
    @Singleton
    internal fun provideStetho(): StethoInterceptor = StethoInterceptor()

    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi =
            Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
}