package com.ahmad.mvvm_pokemontcg.pokemonlist

import com.ahmad.mvvm_pokemontcg.model.PokemonCard

data class PokemonListViewState(
    val loading: Boolean = false,
    val error: Exception? = null,
    val data: MutableList<PokemonCard>? = null
)