package com.ahmad.mvvm_pokemontcg.datastore.card

import com.ahmad.mvvm_pokemontcg.model.PokemonCard

class PokemonCardLocalDataStore : PokemonCardDataStore{
    private val caches = mutableMapOf<String,MutableList<PokemonCard>?>()

    override suspend fun getPokemons(set: Int): MutableList<PokemonCard>? =
        if (caches.contains(set)) caches[set] else null

    override suspend fun addAll(set: Int, pokemons: MutableList<PokemonCard>?) {
        caches[set] = pokemons
    }

}