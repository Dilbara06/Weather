package kg.tutorialapp.weather_app.network

import kg.tutorialapp.weather_app.ForeCast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("onecall?lat=42.882004&lon=74.582748&exclude=minutely&appid=9952fce7867352a4421f69f50af97842&lang=ru&units=metric")
    fun getWeather(): Call<ForeCast>
    @GET("onecall")
    fun fetchWeatherUsingQuerry(
            @Query("lat") lat: Double =42.882004,
            @Query("lon") lon: Double =74.582748,
            @Query("exclude") exclude: String ="minutely",
            @Query("appid") appid: String ="9952fce7867352a4421f69f50af97842",
            @Query("lang") lang: String ="ru",
            @Query("units") units: String ="metric"
    ): Call<ForeCast>
}