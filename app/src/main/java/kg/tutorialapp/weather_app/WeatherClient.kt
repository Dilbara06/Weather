package kg.tutorialapp.weather_app

import kg.tutorialapp.weather_app.network.PostsApi
import kg.tutorialapp.weather_app.network.WeatherApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.*
import retrofit2.converter.gson.GsonConverterFactory

object WeatherClient {
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
//                .baseUrl("https://jsonplaceholder.typicode.com/")
             .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
             .build()

    }

     val weatherApi by lazy{
        retrofit.create(WeatherApi::class.java)
    }

//    private val postsApi by lazy{
//        retrofit.create(PostsApi::class.java)
//    }

}