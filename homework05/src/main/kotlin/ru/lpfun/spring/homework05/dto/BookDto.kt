package ru.lpfun.spring.homework05.dto

import ru.lpfun.spring.homework05.common.models.AuthorModel
import ru.lpfun.spring.homework05.common.models.BookModel
import ru.lpfun.spring.homework05.common.models.GenreModel

class BookDto(
    val id: Long? = null,
    val title: String? = null,
    val authorId: Long? = null,
    val genreId: Long? = null
) {
    fun toModel(author: AuthorModel, genre: GenreModel) = BookModel(
        id = id ?: Long.MIN_VALUE,
        title = title ?: "",
        author = author,
        genre = genre
    )
}