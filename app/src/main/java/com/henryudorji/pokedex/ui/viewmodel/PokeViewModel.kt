package com.henryudorji.pokedex.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.henryudorji.pokedex.data.local.datastore.PrefsDataStore
import com.henryudorji.pokedex.data.model.Pokemon
import com.henryudorji.pokedex.data.repository.PokeDexRepository
import com.henryudorji.pokedex.utils.NetworkManager
import com.henryudorji.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeViewModel @Inject constructor(
    private val repository: PokeDexRepository,
    networkManager: NetworkManager,
    private val prefsDataStore: PrefsDataStore
): ViewModel() {

    private val TAG = "PokeViewModel"
    private val networkObserver = networkManager.observeConnectionStatus

    private val _uiModeLiveData = MutableLiveData<Boolean>()
    val uiModeLiveData: LiveData<Boolean> = _uiModeLiveData

    private val _pokemonLiveData = MutableLiveData<Resource<List<Pokemon>>>()
    val pokemonLiveData: LiveData<Resource<List<Pokemon>>> = _pokemonLiveData

    init {
        readUiMode()

        try {
            getPokemonFromDb()
        }catch (e: Exception) {
            Log.d(TAG, "Get Err_ ${e.message}")
        }
    }

    private fun getPokemonFromDb() = viewModelScope.launch {
        repository.getPokemonFromDb().collect { pokemon ->
            _pokemonLiveData.postValue(Resource.Success(pokemon))
        }
        getPokemonFromApi()
    }

    fun getPokemonFromApi() {
        if (networkObserver.value == true) {
            _pokemonLiveData.postValue(Resource.Loading())
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    repository.deleteAndInsertPokemonFromDb(repository.getPokemonFromApi().pokemon)
                    getPokemonFromDb()
                }catch (e: Exception) {
                    _pokemonLiveData.postValue(Resource.Error("Could not fetch new data"))
                    getPokemonFromDb()
                }
            }
        }
    }

    fun saveUIMode(uiMode: Boolean) = viewModelScope.launch {
        prefsDataStore.saveUIMode(uiMode)
    }

    private fun readUiMode() = viewModelScope.launch {
        prefsDataStore.uiMode.collect { mode ->
            _uiModeLiveData.postValue(mode)
            return@collect
        }
    }
}