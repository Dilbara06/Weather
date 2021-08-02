package kg.tutorialapp.weather_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kg.tutorialapp.weather_app.models.Post
import kg.tutorialapp.weather_app.network.PostsApi
import kg.tutorialapp.weather_app.network.WeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var workResult=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        getWeather()
//        fetchWeatherUsingQuerry()
//        fetchPostById()
//        createPost()
//        createPostUsingFields()
//        createPostUsingFieldMap()
//        updatePost()
//        deletePost()
        setup()
    }

    private fun setup() {
        var btn_start = findViewById<Button>(R.id.btn_start)
        var btn_show_toast = findViewById<Button>(R.id.btn_show_toast)
        btn_start.setOnClickListener {
            //doSomeWork()
            makeRxCall()
        }

        btn_show_toast.setOnClickListener {
            Toast.makeText(this,"Hello", Toast.LENGTH_LONG).show()
        }

    }

    @SuppressLint("CheckResult")
    private fun makeRxCall() {
        WeatherClient.weatherApi.getWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                val tv1 = findViewById<TextView>(R.id.textView4)
                val tv2 = findViewById<TextView>(R.id.textView5)
                tv1.text = it.current?.weather!![0].description
                tv2.text = it.current?.temp?.toString()
            }, {
                Toast.makeText(this,it.message, Toast.LENGTH_LONG).show()
            })
    }

    /// just, create, fromCallable(), formIterable()
    /// disposable, compositeDisposable, clear(), dispose()
    ///map, flatMap, zip,


    private fun doSomeWork() {
            val observable=Observable.create<String> { emitter ->
                Log.d(TAG,"${Thread.currentThread().name} starting emitting")
                Thread.sleep(3000)
                emitter.onNext("Hello")
                Thread.sleep(1000)
                emitter.onNext("Bishkek")
                emitter.onComplete()
            }

            val observer=object: Observer<String>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: String) {
                    Log.d(TAG,"${Thread.currentThread().name} onNext() $t")
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }

            }

        observable
                .subscribeOn(Schedulers.computation())
                .map{
                    Log.d(TAG,"${Thread.currentThread().name} starting mapping")
                    it.toUpperCase()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)

//        var tv_result = findViewById<TextView>(R.id.tv1)
//        Thread(Runnable {
//            for (i in 0..4) {
//                Thread.sleep(1000)
//                workResult++
//            }
//            runOnUiThread {
//                tv_result.text = workResult?.toString()
//            }
//            Handler(Looper.getMainLooper()).post(Runnable {
//                tv1.text=workResult?.toString()
//            })
//        })
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

//    private fun fetchWeatherUsingQuerry() {
//        val call = WeatherClient.weatherApi.fetchWeatherUsingQuerry(lat=40.513996, lon=40.513996)
//        call.enqueue(object : Callback<ForeCast> {
//            override fun onResponse(call: Call<ForeCast>, response: Response<ForeCast>) {
//                if (response.isSuccessful) {
//                    val forecast = response.body()
//                    forecast?.let {
//                        val tv1 = findViewById<TextView>(R.id.textView4)
//                        val tv2 = findViewById<TextView>(R.id.textView5)
//                        tv1.text = it.current?.weather!![0].description
//                        tv2.text = it.current?.temp?.toString()
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<ForeCast>, t: Throwable) {
//                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
//            }
//        })
//    }

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

companion object{
    const val TAG="RX"
}
}