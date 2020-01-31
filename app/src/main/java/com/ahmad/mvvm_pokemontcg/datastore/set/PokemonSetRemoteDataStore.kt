package com.ahmad.mvvm_pokemontcg.datastore.set

import com.ahmad.mvvm_pokemontcg.model.PokemonSet
import com.ahmad.mvvm_pokemontcg.network.PokemonTcgservice
import java.lang.Exception

class PokemonSetRemoteDataStore (private val pokemonTcService: PokemonTcgservice):
    PokemonSetDataStore {
    override suspend fun getSets(): MutableList<PokemonSet>? {
        val response = pokemonTcService.getSets()
        if (response.isSuccessful) return response.body()?.sets

        throw Exception("Terjadi Kesalahan saat merequest data, status error ${response.code()}")
    }

    override suspend fun addAll(sets: MutableList<PokemonSet>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}