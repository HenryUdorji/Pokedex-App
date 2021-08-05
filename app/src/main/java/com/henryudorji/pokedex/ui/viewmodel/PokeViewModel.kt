package com.henryudorji.pokedex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henryudorji.pokedex.data.model.PokeDex
import com.henryudorji.pokedex.data.model.Pokemon
import com.henryudorji.pokedex.data.remote.PokeDexRepository
import com.henryudorji.pokedex.utils.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeViewModel @Inject constructor(
    private val repository: PokeDexRepository,
    networkManager: NetworkManager
): ViewModel() {

    private val networkObserver = networkManager.observeConnectionStatus

    private val _pokeDexLiveData = MutableLiveData<PokeDex>()
    val pokeDexLiveData: LiveData<PokeDex> = _pokeDexLiveData

    init {
        if (networkObserver.value == true) {
            viewModelScope.launch(Dispatchers.IO) {
                _pokeDexLiveData.postValue(repository.getPokeDex())
            }
        }
    }
}