package com.example.afifa123.nearme.Remote

import com.example.afifa123.nearme.Model.MyPlaces
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface IGoogleApiService {
    @GET
    fun getNearByPlaces(@Url url: String):Call<MyPlaces>

}