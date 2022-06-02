package ru.lpfun.spring.homework02.common.interfaces

interface AuthenticateService<T> {
    fun authenticate(): T
}