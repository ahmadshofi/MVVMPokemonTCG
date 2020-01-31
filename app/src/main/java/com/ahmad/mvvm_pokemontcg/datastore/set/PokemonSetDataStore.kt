package com.ahmad.mvvm_pokemontcg.datastore.set

import com.ahmad.mvvm_pokemontcg.model.PokemonSet

interface PokemonSetDataStore {
    suspend fun getSets() : MutableList<PokemonSet>?
    suspend fun addAll(sets: MutableList<PokemonSet>?)
}