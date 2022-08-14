package ru.lpfun.spring.homework05.dto

import ru.lpfun.spring.homework05.common.models.AuthorModel

class AuthorDto(
    val id: Long? = null,
    val author: String? = null
) {
    fun toModel() = AuthorModel(
        id = id ?: Long.MIN_VALUE,
        authorName = author ?: ""
    )
}