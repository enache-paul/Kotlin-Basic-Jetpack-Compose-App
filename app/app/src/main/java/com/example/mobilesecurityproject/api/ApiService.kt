package com.example.mobilesecurityproject.api

import com.example.mobilesecurityproject.api.model.Wizard
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("characters")
    suspend fun getWizardsList() : List<Wizard>


    companion object {
        const val BASE_URL = "https://hp-api.onrender.com/api/"
        var apiService : ApiService? = null

        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }

}