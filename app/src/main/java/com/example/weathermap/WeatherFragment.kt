package com.example.weathermap

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.Manifest
import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.example.weathermap.data.RetrofitInstance
import com.example.weathermap.databinding.FragmentWeatherBinding // Generated ViewBinding class
import com.example.weathermap.model.WeatherRepository

class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!! // This will return the non-nullable binding instance

    private val REQUEST_LOCATION_CODE = 1001

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize ViewBinding
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the repository and ViewModel
        val repository = WeatherRepository(RetrofitInstance.api)
        val factory = WeatherViewModelFactory(repository)
        val weatherViewModel = ViewModelProvider(this, factory).get(WeatherViewModel::class.java)

        // Request location permissions
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_LOCATION_CODE
        )

        // Auto-load the last searched city if it exists
        val lastCity = getLastCity()

        if (!lastCity.isNullOrEmpty()) {
            // Fetch the weather for the last searched city automatically
            weatherViewModel.fetchWeather(lastCity)
            binding.cityEditText.setText(lastCity) // Update EditText to show the last city
        }

        // Set up search button click listener
        binding.searchButton.setOnClickListener {
            val city = binding.cityEditText.text.toString()
            if (city.isNotEmpty()) {
                saveLastCity(city) // Save the last searched city
                weatherViewModel.fetchWeather(city) // Fetch the weather for the new city
            }
        }

        // Observe weather data and update the UI
        weatherViewModel.weatherLiveData.observe(viewLifecycleOwner, Observer { weather ->
            weather?.let {
                // Update basic weather information
                binding.temperatureTextView.text = "Temperature: ${it.main.temp} °C"
                binding.humidityTextView.text = "Humidity: ${it.main.humidity} %"
                binding.windTextView.text = "Wind: ${it.wind.speed} m/s"
                val iconUrl = "http://openweathermap.org/img/wn/${it.weather[0].icon}@2x.png"
                Glide.with(this).load(iconUrl).into(binding.weatherIconImageView)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear the binding when the view is destroyed to avoid memory leaks
    }

    // Save the last searched city
    private fun saveLastCity(city: String) {
        val sharedPreferences = requireActivity().getSharedPreferences("WeatherApp", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("last_city", city).apply()
    }

    // Get the last searched city
    private fun getLastCity(): String? {
        val sharedPreferences = requireActivity().getSharedPreferences("WeatherApp", Context.MODE_PRIVATE)
        return sharedPreferences.getString("last_city", null)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
            }
        }
    }
}
