package ru.lpfun.spring.homework04.common.interfaces

interface MsgProvider {
    fun getMsg(code: String, args: Array<out Any>? = null): String
}