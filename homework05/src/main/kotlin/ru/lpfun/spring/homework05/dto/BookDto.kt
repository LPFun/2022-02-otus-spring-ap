package ru.lpfun.spring.homework05.dto

import ru.lpfun.spring.homework05.common.models.AuthorModel
import ru.lpfun.spring.homework05.common.models.BookModel
import ru.lpfun.spring.homework05.common.models.GenreModel

class BookDto(
    val id: Long? = null,
    val title: String? = null,
    val author: AuthorDto? = null,
    val genre: GenreDto? = null
) {
    fun toModel() = BookModel(
        id = id ?: Long.MIN_VALUE,
        title = title ?: "",
        author = author?.toModel() ?: AuthorModel.NONE,
        genre = genre?.toModel() ?: GenreModel.NONE
    )
}