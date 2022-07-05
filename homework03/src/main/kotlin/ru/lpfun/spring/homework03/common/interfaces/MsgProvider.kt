package ru.lpfun.spring.homework03.common.interfaces

interface MsgProvider {
    fun printMsg(code: String, args: Array<out Any>? = null)
    fun printlnMsg(code: String, args: Array<out Any>? = null)
    fun getMsg(code: String, args: Array<out Any>? = null): String
}