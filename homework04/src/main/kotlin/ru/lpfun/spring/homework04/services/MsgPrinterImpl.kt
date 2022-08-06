package ru.lpfun.spring.homework04.services

import org.springframework.stereotype.Service
import ru.lpfun.spring.homework04.common.interfaces.MsgPrinter
import ru.lpfun.spring.homework04.common.interfaces.MsgProvider
import ru.lpfun.spring.homework04.common.interfaces.io.IOService
import ru.lpfun.spring.homework04.common.interfaces.io.OutputService

@Service
class MsgPrinterImpl(
    private val ioService: IOService,
    private val msgProvider: MsgProvider
) : OutputService by ioService, MsgPrinter {

    override fun printMsg(code: String, args: Array<out Any>?) {
        print(getMsg(code, args))
    }

    override fun printlnMsg(code: String, args: Array<out Any>?) {
        println(getMsg(code, args))
    }

    private fun getMsg(code: String, args: Array<out Any>?): String {
        return msgProvider.getMsg(code, args)
    }
}