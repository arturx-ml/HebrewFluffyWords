package ai.arturxdroid.hebrewfluffywords.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

//https://api.openai.com/v1/images/generations
interface OpenAiApi {
    @Headers("Content-Type: application/json")
    @POST("v1/images/generations")
    fun getUser(@Path("prompt") prompt: String, @Query("size") size:String = "512x512"): Call<List<Image>>?

}