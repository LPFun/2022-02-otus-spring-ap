package ru.lpfun.spring.homework05.dao

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.context.annotation.Import
import ru.lpfun.spring.homework05.common.models.AuthorModel
import ru.lpfun.spring.homework05.common.models.BookModel
import ru.lpfun.spring.homework05.common.models.GenreModel

@JdbcTest
@Import(BookDaoJdbc::class, AuthorDaoJdbc::class, GenreDaoJdc::class)
internal class BookDaoJdbcTest {

    @Autowired
    private lateinit var bookDaoJdbc: BookDaoJdbc

    @Autowired
    private lateinit var genreDaoJdc: GenreDaoJdc

    @Autowired
    private lateinit var authorDaoJdbc: AuthorDaoJdbc

    private val expectedBook = BookModel(
        id = 11,
        title = "название книги",
        author = AuthorModel(
            authorName = "автор"
        ),
        genre = GenreModel(
            genreName = "жанр"
        )
    )

    @Test
    fun `success get all books`() {
        genreDaoJdc.insertIfNotExists(expectedBook.genre)
        authorDaoJdbc.insertIfNotExists(expectedBook.author)
        bookDaoJdbc.insert(expectedBook)
        val books = bookDaoJdbc.getAll()
        assertTrue(books.size == 2)
        assertTrue(books.any { actual ->
            expectedBook.id == actual.id
                    && expectedBook.title == actual.title
                    && expectedBook.author.authorName == actual.author.authorName
                    && expectedBook.genre.genreName == actual.genre.genreName
        })
    }

    @Test
    fun `success get book by id`() {
        val actualBook = bookDaoJdbc.getById(0)

        assertEquals(0, actualBook.id)
        assertNotEquals(BookModel.NONE, actualBook)
    }

    @Test
    fun `success insert book`() {
        authorDaoJdbc.insertIfNotExists(expectedBook.author)
        genreDaoJdc.insertIfNotExists(expectedBook.genre)
        bookDaoJdbc.insert(expectedBook)
        val actualBook = bookDaoJdbc.getById(11)

        assertEquals(expectedBook.id, actualBook.id)
        assertEquals(expectedBook.title, actualBook.title)
        assertEquals(expectedBook.author.authorName, actualBook.author.authorName)
        assertEquals(expectedBook.genre.genreName, actualBook.genre.genreName)
    }

    @Test
    fun `success delete book by id`() {
        authorDaoJdbc.insertIfNotExists(expectedBook.author)
        genreDaoJdc.insertIfNotExists(expectedBook.genre)
        bookDaoJdbc.insert(expectedBook)
        bookDaoJdbc.deleteById(expectedBook.id)

        val actualBooks = bookDaoJdbc.getAll()

        assertTrue(actualBooks.size == 1)
    }

    @Test
    fun `success update book`() {
        authorDaoJdbc.insertIfNotExists(expectedBook.author)
        genreDaoJdc.insertIfNotExists(expectedBook.genre)
        bookDaoJdbc.insert(expectedBook)

        val newAuthor = AuthorModel(
            authorName = "новый автор"
        )
        val newGenre = GenreModel(
            genreName = "новый жанр"
        )
        val newBook = BookModel(
            id = expectedBook.id,
            title = "новое название",
            author = newAuthor,
            genre = newGenre
        )
        genreDaoJdc.insertIfNotExists(newGenre)
        authorDaoJdbc.insertIfNotExists(newAuthor)
        val updatedBook = bookDaoJdbc.update(newBook)
        val actualBook = bookDaoJdbc.getById(newBook.id)

        assertEquals(updatedBook.id, actualBook.id)
        assertEquals(updatedBook.title, actualBook.title)
        assertEquals(updatedBook.genre.genreName, actualBook.genre.genreName)
        assertEquals(updatedBook.author.authorName, actualBook.author.authorName)
    }
}