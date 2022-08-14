package ru.lpfun.spring.homework05.common.dao

import ru.lpfun.spring.homework05.common.models.AuthorModel

interface IAuthorDao {
    fun insertIfNotExists(model: AuthorModel): AuthorModel

    fun getById(id: Long): AuthorModel

    fun update(model: AuthorModel): AuthorModel

    fun getByName(name: String): AuthorModel

    fun getAll(): List<AuthorModel>

    fun deleteById(id: Long)
}