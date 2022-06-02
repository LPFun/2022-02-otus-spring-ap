package ru.lpfun.spring.homework02.services

import ru.lpfun.spring.homework02.common.interfaces.io.IOService

class MockIOService : IOService {
    val outPutArr = mutableListOf<String>()
    val inputArr = mutableListOf<String>()
    private var inputIterationCount = 0

    override fun getInput(): String {
        val input = inputArr[inputIterationCount]
        inputIterationCount = +1
        return input
    }

    override fun print(str: String) {
        outPutArr.add(str)
    }

    override fun println(str: String) {
        outPutArr.add(str)
    }
}