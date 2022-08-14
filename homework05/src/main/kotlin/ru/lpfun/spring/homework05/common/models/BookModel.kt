package ru.lpfun.spring.homework05.common.models

data class BookModel(
    val id: Long = Long.MIN_VALUE,
    val title: String = "",
    val author: AuthorModel = AuthorModel.NONE,
    val genre: GenreModel = GenreModel.NONE
) {
    companion object {
        val NONE = BookModel()
    }
}
