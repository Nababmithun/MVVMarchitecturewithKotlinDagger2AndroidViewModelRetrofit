package com.example.mvvmarchitecturewithkotlindagger2androidviewmodelretrofit.Data

import android.database.Observable
import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubServices {

    @GET("users/{username}")
    fun getUsers(@Path("username") username: String): Observable<JsonObject>
}