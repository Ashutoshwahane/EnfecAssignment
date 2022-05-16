package xyz.cybernerd404.demo.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import xyz.cybernerd404.demo.model.PostModel
import xyz.cybernerd404.demo.model.UserModel

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
interface TestApi {

    /** retrofit instance*/
    companion object {
        operator fun invoke(): TestApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TestApi::class.java)
        }
    }




    @GET("posts")
    suspend fun getPost(): Response<PostModel>

    @GET("users")
    suspend fun getUser(): Response<UserModel>
}