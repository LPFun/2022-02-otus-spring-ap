package ru.lpfun.spring.homework03.common.interfaces

interface Parser<T> {
    fun parse(path: String): List<T>
}
