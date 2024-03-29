package com.example.ehsan.viewmodelsavinginstance.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ehsan.viewmodelsavinginstance.AndroidApplication
import com.example.ehsan.viewmodelsavinginstance.R
import com.example.ehsan.viewmodelsavinginstance.core.Constants
import com.example.ehsan.viewmodelsavinginstance.core.CustomViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.i("${Constants.TIMBER_LOG_TAG}MainActivity OnCreate")

        val repository = (application as AndroidApplication).repository

        mainViewModel = ViewModelProviders.of(this, CustomViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.simpleDataLiveData.observe(this, Observer<Int> {
            simple_data_text_view.text = it.toString()
        })

        load_data_button.setOnClickListener {
            mainViewModel.loadSimpleData()
        }

        savedInstanceState?.let { bundle ->
            Timber.i("${Constants.TIMBER_LOG_TAG}Bundle Not Null")
            val data = bundle[Constants.BUNDLE_KEY_SIMPLE_DATA]
            val simpleData: Int
            if (data is Int) {
                simpleData = data
                mainViewModel.setSimpleData(simpleData)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.let { bundle ->
            mainViewModel.simpleDataLiveData.value?.let { data ->
                Timber.i("${Constants.TIMBER_LOG_TAG}Bundle Data Saved\t$data")
                bundle.putInt(Constants.BUNDLE_KEY_SIMPLE_DATA, data)
            }
        }
    }
}