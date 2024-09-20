WeatherMap Android Application
This is a simple Android application that fetches real-time weather data for any city using the OpenWeatherMap API. The app is built using the MVVM architecture pattern, Retrofit for API calls, Glide for image loading, and ViewBinding for view management.

Features
Search for weather by city name
Display current temperature, humidity, wind speed, and weather icon
Load the last searched city on app launch
Display weather information using data binding
Unit tests for the ViewModel and Repository
Technologies Used
Kotlin: The programming language used for the app.
MVVM: Model-View-ViewModel architecture for separating concerns and maintaining clean code.
Retrofit: For making HTTP requests to the OpenWeatherMap API.
Glide: For loading and displaying weather icons from URLs.
ViewBinding: To simplify view interactions in the code.
JUnit & Mockito: For unit testing the ViewModel and Repository.
Coroutines: For handling asynchronous tasks.


Usage
Enter the name of a city and click Search Weather.
The weather details such as temperature, humidity, wind speed, and weather icon will be displayed.
The last searched city will automatically be loaded when the app is reopened.
Testing
The app includes unit tests for the ViewModel using JUnit and Mockito to verify the behavior of fetching weather data.

