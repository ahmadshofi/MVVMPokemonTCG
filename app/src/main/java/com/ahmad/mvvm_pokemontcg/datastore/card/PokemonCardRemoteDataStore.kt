package com.ahmad.mvvm_pokemontcg.datastore.card

import com.ahmad.mvvm_pokemontcg.model.PokemonCard
import com.ahmad.mvvm_pokemontcg.network.PokemonTcgservice

class PokemonCardRemoteDataStore(private val pokemonTcgService: PokemonTcgservice) : PokemonCardDataStore {
    override suspend fun getPokemons(set: Int): MutableList<PokemonCard>? {
        val response = pokemonTcgService.getCards(set)
        if (response.isSuccessful) return response.body()?.card

        throw Exception("Terjadi kesalahan saat melakukan request data, status error ${response.code()}")
    }

    override suspend fun addAll(set: Int, pokemons: MutableList<PokemonCard>?) {
    }
}
