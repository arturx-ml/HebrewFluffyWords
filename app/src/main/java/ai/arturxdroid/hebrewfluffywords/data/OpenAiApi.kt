package ai.arturxdroid.hebrewfluffywords.data

import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

//https://api.openai.com/v1/images/generations
interface OpenAiApi {
    @Headers("Content-Type: application/json")
    @POST("v1/images/generations")
    suspend fun getImageFromPrompt(@Body json: BodyReq): Response<List<Image>>

}

data class BodyReq(val prompt: String, val size: String = "512x512")