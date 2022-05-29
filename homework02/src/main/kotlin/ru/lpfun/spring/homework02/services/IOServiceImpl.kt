package ru.lpfun.spring.homework02.services

import ru.lpfun.spring.homework02.common.interfaces.IOService
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintStream

class IOServiceImpl(
    private val `in`: InputStream,
    private val out: PrintStream,
) : IOService {
    override fun getInput(): String {
        return BufferedReader(InputStreamReader(`in`)).readLine()
    }

    override fun print(str: String) {
        out.print(str)
    }

    override fun println(str: String) {
        out.println(str)
    }

}