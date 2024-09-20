import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.weathermap.WeatherViewModel
import com.example.weathermap.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.HttpException

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: WeatherRepository

    private lateinit var viewModel: WeatherViewModel
    private val testDispatcher = UnconfinedTestDispatcher()  // Use Unconfined for simplicity

    @Mock
    private lateinit var weatherObserver: Observer<WeatherResponse>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = WeatherViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchWeather should update LiveData when repository returns data`() = runTest {
        // Mocking a fake response with required data
        val fakeMain = Main(temp = 25.0, feels_like = 24.0,pressure = 1013, humidity = 60)
        val fakeWeatherList = listOf(Weather(description = "Clear Sky", icon = "01d"))
        val fakeWind = Wind(speed = 5.0)
        val fakeClouds = Clouds(all = 0)
        val fakeSys = Sys(sunrise = 1620289182, sunset = 1620338556)
        val fakeResponse = WeatherResponse(
            main = fakeMain,
            weather = fakeWeatherList,
            wind = fakeWind,
            clouds = fakeClouds,
            visibility = 10000,
            sys = fakeSys
        )

        // When repository is called, return the fake response
        `when`(repository.getWeather("San Francisco")).thenReturn(fakeResponse)

        // Set the observer on the LiveData
        viewModel.weatherLiveData.observeForever(weatherObserver)

        // Call the fetchWeather method to trigger the coroutine
        viewModel.fetchWeather("San Francisco")

        // Ensure coroutines run to completion
        advanceUntilIdle()

        // Verify that the LiveData is updated with the correct value
        verify(weatherObserver).onChanged(fakeResponse)

        // Verify that the repository method was called once with the correct city name
        verify(repository).getWeather("San Francisco")
    }

    @Test
    fun `fetchWeather should handle HTTP exception`() = runTest {
        // Simulate an HTTP exception when calling the repository
        `when`(repository.getWeather("San Francisco")).thenThrow(HttpException::class.java)

        // Fetch weather to trigger exception handling
        viewModel.fetchWeather("San Francisco")

        // Ensure coroutines run to completion
        advanceUntilIdle()

        // Verify that the repository method was called once
        verify(repository).getWeather("San Francisco")
    }
}
