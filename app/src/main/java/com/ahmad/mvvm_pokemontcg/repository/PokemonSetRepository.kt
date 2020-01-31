package io.github.golok.pokemontcg.repository

import com.ahmad.mvvm_pokemontcg.datastore.set.PokemonSetDataStore
import com.ahmad.mvvm_pokemontcg.model.PokemonSet
import com.ahmad.mvvm_pokemontcg.repository.BaseRepository

class PokemonSetRepository private constructor() : BaseRepository<PokemonSetDataStore>() {
    suspend fun getSets(): MutableList<PokemonSet>? {
        val cache = localDataStore?.getSets()
        if (cache != null) return cache
        val response = remoteDataStore?.getSets()
        localDataStore?.addAll(response)
        return response
    }

    companion object {
        val instance by lazy { PokemonSetRepository() }
    }
}

