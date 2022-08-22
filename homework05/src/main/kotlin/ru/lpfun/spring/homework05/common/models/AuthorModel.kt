package ru.lpfun.spring.homework05.common.models

data class AuthorModel(
    val id: Long = Long.MIN_VALUE,
    val authorName: String = ""
) {
    companion object {
        val NONE = AuthorModel()
    }
}