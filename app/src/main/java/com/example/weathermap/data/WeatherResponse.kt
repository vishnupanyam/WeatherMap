data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
    val clouds: Clouds,
    val visibility: Int,
    val sys: Sys
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val pressure: Int,
    val humidity: Int
)

data class Weather(
    val description: String,
    val icon: String
)

data class Wind(
    val speed: Double
)

data class Clouds(
    val all: Int
)

data class Sys(
    val sunrise: Long,
    val sunset: Long
)
