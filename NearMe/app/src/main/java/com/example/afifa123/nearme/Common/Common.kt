package com.example.afifa123.nearme.Common

import com.example.afifa123.nearme.Remote.IGoogleApiService
import com.example.afifa123.nearme.Remote.RetrofitClient

object Common {
    private val GOOGLE_API_URL = "https://maps.googleapis.com/"

    val googleApiService:IGoogleApiService
    get() = RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleApiService::class.java)
}