package com.ahmad.mvvm_pokemontcg.network

import com.ahmad.mvvm_pokemontcg.model.PokemonCard
import com.ahmad.mvvm_pokemontcg.model.PokemonSet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonTcgservice {

    @GET("card")
    suspend fun getCards(@Query("set") set:String): Response<PokemonCard.PokemonCardResponse>

    @GET("sets")
    suspend fun getSets() : Response<PokemonSet.PokemonSetResponse>
}