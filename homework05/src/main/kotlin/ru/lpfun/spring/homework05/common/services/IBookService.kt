package ru.lpfun.spring.homework05.common.services

import ru.lpfun.spring.homework05.common.models.BookModel

interface IBookService {

    fun addBook(book: BookModel): BookModel

    fun getBookById(id: Long): BookModel

    fun getAllBooks(): List<BookModel>

    fun deleteBookById(id: Long)

    fun updateBook(book: BookModel): BookModel
}