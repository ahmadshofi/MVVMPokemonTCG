package io.github.golok.pokemontcg.repository

import com.ahmad.mvvm_pokemontcg.datastore.card.PokemonCardDataStore
import com.ahmad.mvvm_pokemontcg.model.PokemonCard
import com.ahmad.mvvm_pokemontcg.repository.BaseRepository

class PokemonCardRepository private constructor() : BaseRepository<PokemonCardDataStore>() {
    suspend fun getPokemons(set: Int): MutableList<PokemonCard>? {
        val cache = localDataStore?.getPokemons(set)
        if (cache != null) return cache
        val response = remoteDataStore?.getPokemons(set)
        localDataStore?.addAll(set, response)
        return response
    }

    companion object {
        val instance by lazy { PokemonCardRepository() }
    }
}