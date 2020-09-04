package com.shouman.apps.weatherstation.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.shouman.apps.weatherstation.api.networkModel.CurrentTemp
import com.shouman.apps.weatherstation.api.networkModel.DailyCondition5Days
import com.shouman.apps.weatherstation.api.networkModel.HourlyCondition
import com.shouman.apps.weatherstation.api.networkModel.Location
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private val BASE_URL = "http://dataservice.accuweather.com/"


/**
 * Build the Moshi object that Retrofit will be using, add Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi by lazy {
    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
}


//private val client by lazy {
//    OkHttpClient.Builder()
//        .build()
//}


private val retrofit by lazy {

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()


    Retrofit
        .Builder()
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()
}


interface LocationApiServices {

    @GET("/locations/v1/cities/geoposition/search")
    fun getCurrentLocationAsync(
        @Query("apikey") apiKey: String,
        @Query("q") latLong: String,
        @Query("details") requireDetails: Boolean,
        @Query("toplevel") onlyTopLevel: Boolean
    ): Deferred<Location>
}


interface CurrentConditionApiServices {

    @GET("currentconditions/v1/{locationKey}")
    fun getCurrentConditionAsync(
        @Path("locationKey") locationKey: Long,
        @Query("apikey") apiKey: String,
        @Query("language") language: String,
        @Query("details") details: Boolean
    ): Deferred<List<CurrentTemp>>
}


interface Next12HourApiServices {

    @GET("/forecasts/v1/hourly/12hour/{locationKey}")
    fun getNext12HourConditionAsync(
        @Path("locationKey") locationKey: Long,
        @Query("apikey") apiKey: String,
        @Query("language") language: String,
        @Query("details") details: Boolean,
        @Query("metric") metric: Boolean
    ): Deferred<List<HourlyCondition>>
}


interface Next5DaysApiServices {

    @GET("/forecasts/v1/daily/5day/{locationKey}")
    fun getNext5DaysConditionAsync(
        @Path("locationKey") locationKey: Long,
        @Query("apikey") apiKey: String,
        @Query("language") language: String,
        @Query("details") details: Boolean,
        @Query("metric") metric: Boolean
    ): Deferred<DailyCondition5Days>
}


object NetworkCall {
    val locationService: LocationApiServices by lazy {
        retrofit.create(LocationApiServices::class.java)
    }

    val currentConditionsService: CurrentConditionApiServices by lazy {
        retrofit.create(CurrentConditionApiServices::class.java)
    }

    val next12HourApiServices: Next12HourApiServices by lazy {
        retrofit.create(Next12HourApiServices::class.java)
    }

    val next5DaysApiServices: Next5DaysApiServices by lazy {
        retrofit.create(Next5DaysApiServices::class.java)
    }
}
