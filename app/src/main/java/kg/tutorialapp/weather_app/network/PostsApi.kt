package kg.tutorialapp.weather_app.network

import kg.tutorialapp.weather_app.models.Post
import retrofit2.Call
import retrofit2.http.*

interface PostsApi {
    @GET("posts/{id}")
    fun fetchPostByID(
            @Path("id") id: Int=1
    ): Call<Post>

    @POST("posts")
    fun createPost(
            @Body post: Post
    ): Call<Post>


    @POST("posts")
    @FormUrlEncoded
    fun createPostUsingFields(
            @Field("userId") userId:Int,
            @Field("title") title: String,
            @Field("body") body: String
    ): Call<Post>

    @POST("posts")
    @FormUrlEncoded
    fun createPostUsingFieldMap(
          @FieldMap map: Map<String,String>
    ): Call<Post>

    @PUT("posts/{id}")
    fun putPost(
            @Path("id") id:String,
            @Body post: Post
    ): Call<Post>

    @PATCH("posts/{id}")
    fun patchPost(
            @Path("id") id:String,
            @Body post: Post
    ): Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: String):Call<Unit>
}