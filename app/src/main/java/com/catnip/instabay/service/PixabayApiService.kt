package com.catnip.instabay.service

import com.catnip.instabay.BuildConfig
import com.catnip.instabay.model.SearchResponse
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface PixabayApiService {

    @GET("api")
    suspend fun searchPhoto(
        @Query("q", encoded = true) query: String,
        @Query("image_type") type: String = DEFAULT_IMAGE_TYPE
    ): SearchResponse

    companion object {
        const val DEFAULT_IMAGE_TYPE = "photo"

        @JvmStatic
        operator fun invoke(chuckerInterceptor: ChuckerInterceptor): PixabayApiService {
            val authInterceptor = Interceptor {
                val originRequest = it.request()
                val newUrl = originRequest.url.newBuilder().apply {
                    addQueryParameter("key", BuildConfig.API_KEY)
                }.build()
                it.proceed(originRequest.newBuilder().url(newUrl).build())
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(chuckerInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(PixabayApiService::class.java)
        }
    }
}