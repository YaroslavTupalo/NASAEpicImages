package com.yaroslavtupalo.nasaepicimages.data.local

import android.content.Context
import android.content.SharedPreferences
import com.yaroslavtupalo.nasaepicimages.dagger.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Yaroslav on 01.02.2018.
 */
@Singleton
class PreferencesHelper @Inject constructor(@ApplicationContext context: Context){

    private val preferences: SharedPreferences

    companion object {
        val PREF_FILE_NAME = "mvpnasa_pref_file"
    }

    init {
        preferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun clear(){
        preferences.edit().clear().apply()
    }
}