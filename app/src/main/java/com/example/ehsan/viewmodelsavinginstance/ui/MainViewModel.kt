package com.example.ehsan.viewmodelsavinginstance.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ehsan.viewmodelsavinginstance.core.Constants
import com.example.ehsan.viewmodelsavinginstance.data.repository.SimpleDataRepository
import timber.log.Timber

class MainViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: SimpleDataRepository
) : ViewModel() {
    val simpleDataLiveData = savedStateHandle.getLiveData<Int>(Constants.BUNDLE_KEY_SIMPLE_DATA)

    init {
        Timber.i("${Constants.TIMBER_LOG_TAG}MainViewModel Constructor\t$this")
    }

    fun loadSimpleData() {
        setSimpleData(repository.simpleData)
    }

    fun setSimpleData(data: Int) {
        Timber.i("${Constants.TIMBER_LOG_TAG}ViewModel setData")
        savedStateHandle.set(Constants.BUNDLE_KEY_SIMPLE_DATA, data)
    }
}