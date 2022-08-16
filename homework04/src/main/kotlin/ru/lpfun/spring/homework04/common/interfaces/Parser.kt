package ru.lpfun.spring.homework04.common.interfaces

interface Parser<T> {
    fun parse(path: String): List<T>
}
