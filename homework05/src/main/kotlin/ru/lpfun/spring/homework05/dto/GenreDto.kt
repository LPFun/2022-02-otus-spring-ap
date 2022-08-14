package ru.lpfun.spring.homework05.dto

import ru.lpfun.spring.homework05.common.models.GenreModel

class GenreDto(
    val id: Long?,
    val genre: String?
) {
    fun toModel() = GenreModel(
        id = id ?: Long.MIN_VALUE,
        genre = genre ?: ""
    )
}