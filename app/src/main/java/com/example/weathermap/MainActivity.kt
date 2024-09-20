package com.example.weathermap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check if the fragment_container is already occupied by a fragment
        if (savedInstanceState == null) {
            // If not, create a new WeatherFragment and add it
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, WeatherFragment())
                .commit()
        }
    }
}

