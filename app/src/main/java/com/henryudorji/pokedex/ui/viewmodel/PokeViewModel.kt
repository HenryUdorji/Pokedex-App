package com.henryudorji.pokedex.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.henryudorji.pokedex.data.remote.PokeDexRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokeViewModel @Inject constructor(
    private val pokeDexRepository: PokeDexRepository
): ViewModel() {

}