package com.example.weathermap.model

import WeatherResponse
import android.util.Log
import com.example.weathermap.data.WeatherApi

class WeatherRepository(private val api: WeatherApi) {

    // Hardcoded API Key
    private val apiKey = "1a76f60fee1a10a3d1d47fb0ebb1d0c2"

    suspend fun getWeather(city: String): WeatherResponse {
        Log.d("WeatherRepository", "Fetching weather for city: $city")

        // Make the API call
        try {
            val response = api.getWeather(city, apiKey)  // Don't manually encode city
            Log.d("WeatherRepository", "API call successful. Weather data received: ${response.main.temp}")
            return response
        } catch (e: Exception) {
            Log.d("WeatherRepository", "API call failed: ${e.message}")
            throw e
        }
    }
}







