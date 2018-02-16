package com.yaroslavtupalo.nasaepicimages.data.moshi

import com.squareup.moshi.Json

/**
 * Created by Yaroslav on 01.02.2018.
 */

data class AttitudeQuaternions(@Json(name = "q0") val q0: Double,
                               @Json(name = "q1") val q1: Double,
                               @Json(name = "q2") val q2: Double,
                               @Json(name = "q3") val q3: Double)

data class AttitudeQuaternions_(@Json(name = "q0") val q0: Double,
                                @Json(name = "q1") val q1: Double,
                                @Json(name = "q2") val q2: Double,
                                @Json(name = "q3") val q3: Double)

data class CentroidCoordinates(@Json(name = "lat") val lat: Double,
                               @Json(name = "lon") val lon: Double)

data class CentroidCoordinates_ (@Json(name = "lat") val lat: Double,
                                 @Json(name = "lon") val lon: Double)

data class Coords (@Json(name = "centroid_coordinates") val centroid_coordinates: CentroidCoordinates_ ,
                   @Json(name = "dscovr_j2000_position") val dscovr_j2000_position: DscovrJ2000Position_,
                   @Json(name = "lunar_j2000_position") val lunar_j2000_position: LunarJ2000Position_,
                   @Json(name = "sun_j2000_position") val sun_j2000_position: SunJ2000Position_,
                   @Json(name = "attitude_quaternions") val attitude_quaternions: AttitudeQuaternions_ )

data class DscovrJ2000Position (@Json(name = "x") val x: Double,
                                @Json(name = "y") val y: Double,
                                @Json(name = "z") val z: Double)

data class DscovrJ2000Position_ (@Json(name = "x") val x: Double,
                                 @Json(name = "y") val y: Double,
                                 @Json(name = "z") val z: Double)

data class Example (@Json(name = "identifier") val identifier: String,
                    @Json(name = "caption") val caption: String,
                    @Json(name = "image") val image: String,
                    @Json(name = "version") val version: String,
                    @Json(name = "centroid_coordinates") val centroid_coordinates: CentroidCoordinates,
                    @Json(name = "dscovr_j2000_position") val dscovr_j2000_position: DscovrJ2000Position,
                    @Json(name = "lunar_j2000_position") val lunar_j2000_position: LunarJ2000Position,
                    @Json(name = "sun_j2000_position") val sun_j2000_position: SunJ2000Position,
                    @Json(name = "attitude_quaternions") val attitude_quaternions: AttitudeQuaternions,
                    @Json(name = "date") val date: String,
                    @Json(name = "coords") val coords: Coords)

data class LunarJ2000Position (@Json(name = "x") val x: Double,
                               @Json(name = "y") val y: Double,
                               @Json(name = "z") val z: Double)

data class LunarJ2000Position_  (@Json(name = "x") val x: Double,
                                 @Json(name = "y") val y: Double,
                                 @Json(name = "z") val z: Double)

data class SunJ2000Position (@Json(name = "x") val x: Double,
                             @Json(name = "y") val y: Double,
                             @Json(name = "z") val z: Double)

data class SunJ2000Position_(@Json(name = "x") val x: Double,
                             @Json(name = "y") val y: Double,
                             @Json(name = "z") val z: Double)