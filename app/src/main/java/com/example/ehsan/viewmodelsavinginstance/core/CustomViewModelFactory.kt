package com.example.ehsan.viewmodelsavinginstance.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ehsan.viewmodelsavinginstance.data.repository.SimpleDataRepository
import com.example.ehsan.viewmodelsavinginstance.ui.MainViewModel

class CustomViewModelFactory(private val repository: SimpleDataRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}