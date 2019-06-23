package com.example.ehsan.viewmodelsavinginstance.data.repository

import kotlin.random.Random

class SimpleDataRepository {
    val simpleData: Int
        get() = Random.nextInt(0, 1000)
}