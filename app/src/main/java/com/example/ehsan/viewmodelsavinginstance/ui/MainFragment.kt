package com.example.ehsan.viewmodelsavinginstance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ehsan.viewmodelsavinginstance.AndroidApplication
import com.example.ehsan.viewmodelsavinginstance.R
import com.example.ehsan.viewmodelsavinginstance.core.Constants
import com.example.ehsan.viewmodelsavinginstance.core.CustomViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.view.*
import timber.log.Timber

class MainFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("${Constants.TIMBER_LOG_TAG}Fragment OnCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.i("${Constants.TIMBER_LOG_TAG}Fragment OnCreateView")

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val repository = (requireActivity().application as AndroidApplication).repository

        mainViewModel = ViewModelProviders.of(
            this,
            CustomViewModelFactory(repository)
        )[MainViewModel::class.java]

        mainViewModel.simpleDataLiveData.observe(this, Observer<Int> {
            view.simple_data_text_view.text = it.toString()
        })

        view.load_data_button.setOnClickListener {
            mainViewModel.loadSimpleData()
        }

        if (savedInstanceState != null) {
            Timber.i("${Constants.TIMBER_LOG_TAG}Bundle Not Null")
            val data = savedInstanceState[Constants.BUNDLE_KEY_SIMPLE_DATA]
            val simpleData: Int
            if (data is Int) {
                simpleData = data
                mainViewModel.setSimpleData(simpleData)
            }
        }

        return view
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        super.onSaveInstanceState(bundle)

        mainViewModel.simpleDataLiveData.value?.let { data ->
            Timber.i("${Constants.TIMBER_LOG_TAG}Bundle Data Saved\t$data")
            bundle.putInt(Constants.BUNDLE_KEY_SIMPLE_DATA, data)
        }

    }

}