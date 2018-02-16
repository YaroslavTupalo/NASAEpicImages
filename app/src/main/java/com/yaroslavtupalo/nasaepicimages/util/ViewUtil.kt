package com.yaroslavtupalo.nasaepicimages.util

import android.content.res.Resources

/**
 * Created by Yaroslav on 02.02.2018.
 */
object ViewUtil {

    fun pxToDp(px: Float): Float{
        val densityDpi = Resources.getSystem().displayMetrics.densityDpi.toFloat()
        return px/(densityDpi / 160f)
    }

    fun dpToPx(dp: Int): Int{
        val density = Resources.getSystem().displayMetrics.density
        return Math.round(dp * density)
    }
}