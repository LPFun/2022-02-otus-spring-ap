package ru.lpfun.spring.homework05.common.models

data class GenreModel(
    val id: Long = Long.MIN_VALUE,
    val genreName: String = ""
) {
    companion object {
        val NONE = GenreModel()
    }
}
