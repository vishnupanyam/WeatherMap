package com.example.weathermap

import WeatherResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermap.model.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException


import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class WeatherViewModel(
    private val repository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _weatherLiveData = MutableLiveData<WeatherResponse>()
    val weatherLiveData: LiveData<WeatherResponse> get() = _weatherLiveData

    fun fetchWeather(city: String) {
        viewModelScope.launch(dispatcher) {
            try {
                val response = repository.getWeather(city)
                _weatherLiveData.postValue(response)
            } catch (e: HttpException) {
            }
        }
    }
}





