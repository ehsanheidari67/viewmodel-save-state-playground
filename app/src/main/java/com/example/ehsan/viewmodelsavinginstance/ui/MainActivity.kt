package com.example.ehsan.viewmodelsavinginstance.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ehsan.viewmodelsavinginstance.R
import com.example.ehsan.viewmodelsavinginstance.core.Constants
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.i("${Constants.TIMBER_LOG_TAG}MainActivity OnCreate")

        val fragment = MainFragment()

        if (savedInstanceState == null) {
            Timber.i("${Constants.TIMBER_LOG_TAG}Fragment Transaction Commit")
            supportFragmentManager.beginTransaction().add(
                R.id.fragment_container,
                fragment
            ).commit()
        }
    }
}