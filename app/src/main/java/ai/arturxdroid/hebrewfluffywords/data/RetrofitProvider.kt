package ai.arturxdroid.hebrewfluffywords.data

import ai.arturxdroid.HebrewFluffyWords.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    private val BASE_URL = "https://api.openai.com/"

    fun getOpenApi(): OpenAiApi = getClient()
        .create(OpenAiApi::class.java)

    private fun getClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient())
            .build()

    private fun getHttpClient(): OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val req = chain.request()
        val headers = req.headers().newBuilder().add(
            "Authorization", "Bearer ${BuildConfig.OPEN_AI_KEY}"
        ).add(
            "Content-Type", "application/json"
        ).build()
        val newRequest = req.newBuilder().headers(headers).build()
        chain.proceed(newRequest)
    }.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()
}

