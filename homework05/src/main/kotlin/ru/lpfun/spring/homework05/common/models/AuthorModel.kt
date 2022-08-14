package ru.lpfun.spring.homework05.common.models

data class AuthorModel(
    val id: Long = Long.MIN_VALUE,
    val author: String = ""
) {
    companion object {
        val NONE = AuthorModel()
    }
}