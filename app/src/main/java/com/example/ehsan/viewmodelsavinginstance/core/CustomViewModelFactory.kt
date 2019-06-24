package com.example.ehsan.viewmodelsavinginstance.core

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateVMFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.ehsan.viewmodelsavinginstance.data.repository.SimpleDataRepository
import com.example.ehsan.viewmodelsavinginstance.ui.MainViewModel

class CustomViewModelFactory(private val owner: SavedStateRegistryOwner,
                             private val bundle: Bundle?,
                             private val repository: SimpleDataRepository) :
    AbstractSavedStateVMFactory(owner, bundle){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return MainViewModel(handle, repository) as T
    }
}