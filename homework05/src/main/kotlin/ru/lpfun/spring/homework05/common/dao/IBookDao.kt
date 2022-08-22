package ru.lpfun.spring.homework05.common.dao

import ru.lpfun.spring.homework05.common.models.BookModel

interface IBookDao {
    fun insert(model: BookModel): BookModel

    fun getById(id: Long): BookModel

    fun update(model: BookModel): BookModel

    fun getAll(): List<BookModel>

    fun deleteById(id: Long)
}