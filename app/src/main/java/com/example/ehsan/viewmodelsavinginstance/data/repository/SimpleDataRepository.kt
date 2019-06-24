package com.example.ehsan.viewmodelsavinginstance.data.repository

import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class SimpleDataRepository {
    val simpleData: Int
        get() = Random.nextInt(0, 1000)

    val networkResult: Observable<Int>
        get() = Observable.timer(5, TimeUnit.SECONDS).map {
            simpleData
        }
}