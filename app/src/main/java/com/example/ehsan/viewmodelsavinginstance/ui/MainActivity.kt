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

        mainViewModel = ViewModelProviders.of(this,
            CustomViewModelFactory(this, savedInstanceState, repository))[MainViewModel::class.java]

        mainViewModel.simpleDataLiveData.observe(this, Observer<Int> {
            simple_data_text_view.text = it.toString()
        })

        load_data_button.setOnClickListener {
            mainViewModel.loadSimpleData()
        }
    }
}