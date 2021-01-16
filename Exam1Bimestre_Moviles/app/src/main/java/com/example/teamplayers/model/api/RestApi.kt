package com.example.teamplayers.model.api

import com.example.teamplayers.model.data.Team
import com.example.teamplayers.model.data.PLayingPlayers
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Class includes definition of web API
 */
interface RestApi {

    @GET("api_url_path")
    fun getRecipes() : Observable<List<PLayingPlayers>>

    @GET("api_url_path")
    fun getCategories() : Observable<List<Team>>
}
