package com.yaroslavtupalo.nasaepicimages.app

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * Created by Yaroslav on 16.02.2018.
 * This class solved the problem with Glide Error:
 * "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler
 * in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored"
 * P.S. Very strange solution
 */
@GlideModule
class MyAppGlideModule: AppGlideModule()