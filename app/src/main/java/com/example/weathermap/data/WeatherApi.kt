package com.example.weathermap.data

import WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,    // City name
        @Query("appid") apiKey: String  // API Key
    ): WeatherResponse
}



