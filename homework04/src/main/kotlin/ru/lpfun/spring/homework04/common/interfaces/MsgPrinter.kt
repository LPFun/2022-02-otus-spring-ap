package ru.lpfun.spring.homework04.common.interfaces

interface MsgPrinter {
    fun printMsg(code: String, args: Array<out Any>? = null)
    fun printlnMsg(code: String, args: Array<out Any>? = null)
}