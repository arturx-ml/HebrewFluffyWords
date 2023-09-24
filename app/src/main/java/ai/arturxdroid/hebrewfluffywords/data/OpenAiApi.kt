package ai.arturxdroid.hebrewfluffywords.data

import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

//https://api.openai.com/v1/images/generations
interface OpenAiApi {
    @Headers("Content-Type: application/json")
    @POST("v1/images/generations")
    suspend fun getImageFromPrompt(@Path("prompt") prompt: String, @Query("size") size:String = "512x512"): Response<List<Image>>

}