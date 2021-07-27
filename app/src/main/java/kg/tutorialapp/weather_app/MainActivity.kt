package kg.tutorialapp.weather_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import kg.tutorialapp.weather_app.models.Post
import kg.tutorialapp.weather_app.network.PostsApi
import kg.tutorialapp.weather_app.network.WeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        getWeather()
        fetchWeatherUsingQuerry()
//        fetchPostById()
//        createPost()
//        createPostUsingFields()
//        createPostUsingFieldMap()
//        updatePost()
//        deletePost()
    }

//    private fun deletePost() {
//        val call=postsApi.deletePost("42")
//        call.enqueue(object:Callback<Unit>{
//            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
//                val tv1 = findViewById<TextView>(R.id.textView4)
//                tv1.text=response.code().toString()
//            }
//
//            override fun onFailure(call: Call<Unit>, t: Throwable) {
//
//            }
//
//        })
//    }
//
//    private fun updatePost() {
//        val newPost=Post(userId = "20", body = "this is body")
//        val call=postsApi.patchPost(id="42", post=newPost)
//        call.enqueue(object:Callback<Post>{
//            override fun onResponse(call: Call<Post>, response: Response<Post>) {
//                val resultPost=response.body()
//                resultPost?.let{
//                    val resultText="ID: "+it.id +"\n"+
//                            "userId: "+it.userId+"\n"+
//                            "title: "+ it.title +"\n"+
//                            "body: "+ it.body+ "\n"
//                    val tv1 = findViewById<TextView>(R.id.textView4)
//                    tv1.text=resultText
//                }
//            }
//
//            override fun onFailure(call: Call<Post>, t: Throwable) {
//
//            }
//    })
//    }
//
//
//    private fun createPostUsingFieldMap() {
//        val map=HashMap<String,String>().apply {
//            put("userId","55")
//            put("title","Hi!!!")
//            put("body","Issyk Kul")
//        }
//        val call=postsApi.createPostUsingFieldMap(map)
//        call.enqueue(object:Callback<Post>{
//            override fun onResponse(call: Call<Post>, response: Response<Post>) {
//                val resultPost=response.body()
//                resultPost?.let{
//                    val resultText="ID: "+it.id +"\n"+
//                            "userId: "+it.userId+"\n"+
//                            "title: "+ it.title +"\n"+
//                            "body: "+ it.body+ "\n"
//                    val tv1 = findViewById<TextView>(R.id.textView4)
//                    tv1.text=resultText
//                }
//            }
//
//            override fun onFailure(call: Call<Post>, t: Throwable) {
//
//            }
//
//        })
//    }
//
//    private fun createPostUsingFields() {
//        val call=postsApi.createPostUsingFields(userId = 99,title = "High",body = "Osh")
//        call.enqueue(object:Callback<Post>{
//            override fun onResponse(call: Call<Post>, response: Response<Post>) {
//                val resultPost=response.body()
//                resultPost?.let{
//                    val resultText="ID: "+it.id +"\n"+
//                            "userId: "+it.userId+"\n"+
//                            "title: "+ it.title +"\n"+
//                            "body: "+ it.body+ "\n"
//                    val tv1 = findViewById<TextView>(R.id.textView4)
//                    tv1.text=resultText
//                }
//            }
//
//            override fun onFailure(call: Call<Post>, t: Throwable) {
//
//            }
//
//        })
//    }
//
//    private fun createPost() {
//         val post=Post(userId="42",title="Hello",body = "Bishkek")
//        val call=postsApi.createPost(post)
//        call.enqueue(object:Callback<Post>{
//            override fun onResponse(call: Call<Post>, response: Response<Post>) {
//                val resultPost=response.body()
//                resultPost?.let{
//                    val resultText="ID: "+it.id +"\n"+
//                            "userId: "+it.userId+"\n"+
//                            "title: "+ it.title +"\n"+
//                            "body: "+ it.body+ "\n"
//                    val tv1 = findViewById<TextView>(R.id.textView4)
//                    tv1.text=resultText
//                }
//            }
//
//            override fun onFailure(call: Call<Post>, t: Throwable) {
//
//            }
//
//        })
//
//    }
//
//    private fun fetchPostById() {
//        val call=postsApi.fetchPostByID(10)
//        call.enqueue(object:Callback<Post>{
//            override fun onResponse(call: Call<Post>, response: Response<Post>) {
//                val post=response.body()
//                post?.let{
//                    val resultText="ID: "+it.id +"\n"+
//                            "userId: "+it.userId+"\n"+
//                            "title: "+ it.title +"\n"+
//                            "body: "+ it.body+ "\n"
//                    val tv1 = findViewById<TextView>(R.id.textView4)
//                    val tv2 = findViewById<TextView>(R.id.textView5)
//                    tv1.text=resultText
//                }
//            }
//
//            override fun onFailure(call: Call<Post>, t: Throwable) {
//
//            }
//
//        })
//    }

    private fun fetchWeatherUsingQuerry() {
        val call = WeatherClient.weatherApi.fetchWeatherUsingQuerry(lat=40.513996, lon=40.513996)
        call.enqueue(object : Callback<ForeCast> {
            override fun onResponse(call: Call<ForeCast>, response: Response<ForeCast>) {
                if (response.isSuccessful) {
                    val forecast = response.body()
                    forecast?.let {
                        val tv1 = findViewById<TextView>(R.id.textView4)
                        val tv2 = findViewById<TextView>(R.id.textView5)
                        tv1.text = it.current?.weather!![0].description
                        tv2.text = it.current?.temp?.toString()
                    }
                }
            }

            override fun onFailure(call: Call<ForeCast>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

//    private fun getWeather() {
//        val call = weatherApi.getWeather()
//        call.enqueue(object : Callback<ForeCast> {
//            override fun onResponse(call: Call<ForeCast>, response: Response<ForeCast>) {
//                if (response.isSuccessful) {
//                    val forecast = response.body()
//                    forecast?.let {
//                        val tv1 = findViewById<TextView>(R.id.textView4)
//                        val tv2 = findViewById<TextView>(R.id.textView5)
//                        tv1.text = it.current?.weather!![0].description
//                        tv2.text = it.current?.temp?.toString()
//
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<ForeCast>, t: Throwable) {
//                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
//            }
//        })
//    }


}