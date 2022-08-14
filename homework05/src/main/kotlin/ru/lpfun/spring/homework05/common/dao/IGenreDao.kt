package ru.lpfun.spring.homework05.common.dao

import ru.lpfun.spring.homework05.common.models.GenreModel

interface IGenreDao {
    fun insert(model: GenreModel): GenreModel

    fun getById(id: Long): GenreModel

    fun update(model: GenreModel): GenreModel

    fun getAll(): List<GenreModel>

    fun deleteById(id: Long)
}