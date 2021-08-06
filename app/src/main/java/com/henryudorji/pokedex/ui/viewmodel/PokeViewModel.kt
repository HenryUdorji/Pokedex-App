package com.henryudorji.pokedex.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henryudorji.pokedex.data.local.datastore.PrefsDataStore
import com.henryudorji.pokedex.data.model.PokeDex
import com.henryudorji.pokedex.data.model.Pokemon
import com.henryudorji.pokedex.data.remote.PokeDexRepository
import com.henryudorji.pokedex.utils.NetworkManager
import com.henryudorji.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeViewModel @Inject constructor(
    private val repository: PokeDexRepository,
    networkManager: NetworkManager,
    private val prefsDataStore: PrefsDataStore
): ViewModel() {

    private val networkObserver = networkManager.observeConnectionStatus

    private val _uiModeLiveData = MutableLiveData<Boolean>()
    val uiModeLiveData: LiveData<Boolean> = _uiModeLiveData

    private val _pokeDexLiveData = MutableLiveData<Resource<PokeDex>>()
    val pokeDexLiveData: LiveData<Resource<PokeDex>> = _pokeDexLiveData

    init {
        readUiMode()

        if (networkObserver.value == true) {
            _pokeDexLiveData.postValue(Resource.Loading())
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    _pokeDexLiveData.postValue(Resource.Success(repository.getPokeDex()))
                }catch (e: Exception) {
                    if (e.message != null) {
                        _pokeDexLiveData.postValue(Resource.Error(e.message!!))
                    }else _pokeDexLiveData.postValue(Resource.Error("Error occurred"))
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
        }
    }
}