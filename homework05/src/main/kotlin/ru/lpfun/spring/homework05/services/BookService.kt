package ru.lpfun.spring.homework05.services

import org.springframework.stereotype.Service
import ru.lpfun.spring.homework05.common.dao.IAuthorDao
import ru.lpfun.spring.homework05.common.dao.IBookDao
import ru.lpfun.spring.homework05.common.dao.IGenreDao
import ru.lpfun.spring.homework05.common.models.BookModel
import ru.lpfun.spring.homework05.common.services.IBookService

@Service
class BookService(
    private val bookDao: IBookDao,
    private val genreDao: IGenreDao,
    private val authorDao: IAuthorDao,
) : IBookService {
    override fun addBook(book: BookModel): BookModel {
        genreDao.insertIfNotExists(book.genre)
        authorDao.insertIfNotExists(book.author)
        bookDao.insertIfNotExists(book)
        return book
    }

    override fun getBookById(id: Long): BookModel {
        return bookDao.getById(id)
    }

    override fun getAllBooks(): List<BookModel> {
        return bookDao.getAll()
    }

    override fun deleteBookById(id: Long) {
        bookDao.deleteById(id)
    }

    override fun updateBook(book: BookModel): BookModel {
        authorDao.insertIfNotExists(book.author)
        genreDao.insertIfNotExists(book.genre)
        genreDao.update(book.genre)
        return bookDao.update(book)
    }
}