package com.example.customerserviceapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.customerserviceapp.model.CleaningS
import com.example.customerserviceapp.repository.CleaningSRepository
import kotlinx.coroutines.launch

class CleaningsViewModel (private val repository: CleaningSRepository): ViewModel(){
    val allCleaningS: LiveData<List<CleaningS>> = repository.allCleanings.asLiveData()

    fun insert(cleaningS: CleaningS) = viewModelScope.launch {
        repository.insertCleaningS(cleaningS)
    }

    fun delete(cleaningS: CleaningS) = viewModelScope.launch {
        repository.deleteCleaningS(cleaningS)
    }
    fun update(cleaningS: CleaningS) = viewModelScope.launch {
        repository.updateCleaningS(cleaningS)
    }
}

class CleaningsViewModelFactory(private val repository: CleaningSRepository): ViewModelProvider.Factory{
    override
    fun <T : ViewModel> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom((CleaningsViewModel::class.java))){
            return CleaningsViewModel(repository) as T
       }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}