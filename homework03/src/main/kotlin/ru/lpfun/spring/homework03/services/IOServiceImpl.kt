package ru.lpfun.spring.homework03.services

import org.springframework.stereotype.Service
import ru.lpfun.spring.homework03.common.interfaces.io.IOService
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintStream

@Service
class IOServiceImpl(
    private val `in`: InputStream = System.`in`,
    private val out: PrintStream = System.out,
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