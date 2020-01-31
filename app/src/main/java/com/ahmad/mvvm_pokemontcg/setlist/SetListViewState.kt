package com.ahmad.mvvm_pokemontcg.setlist

import com.ahmad.mvvm_pokemontcg.model.PokemonSet
import java.lang.Exception

data class SetListViewState(
    var loading: Boolean = false,
    var error : Exception? = null,
    var data : MutableList<PokemonSet>? = null
)