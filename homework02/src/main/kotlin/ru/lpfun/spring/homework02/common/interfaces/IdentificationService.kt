package ru.lpfun.spring.homework02.common.interfaces

interface IdentificationService<T> {
    fun identificate(): T
}