package ru.lpfun.spring.homework02.common.interfaces

interface Parser<T> {
    fun parse(path: String): List<T>
}
