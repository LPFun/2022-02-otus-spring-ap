package ru.lpfun.spring.homework01.common.interfaces

interface Parser<T> {
    fun parse(path: String): List<T>
}
