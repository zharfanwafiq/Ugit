package com.orainjistudio.ugit.data.api

import com.orainjistudio.ugit.data.response.detailuser.DetailUser
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET(UrlEndPoint.ALL_USERS)
    fun getAllUsers(): Call<ArrayList<DetailUser>>
}