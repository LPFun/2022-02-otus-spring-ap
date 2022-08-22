package ru.lpfun.spring.homework05.dao

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.context.annotation.Import
import ru.lpfun.spring.homework05.common.models.AuthorModel

@JdbcTest
@Import(AuthorDaoJdbc::class)
internal class AuthorDaoJdbcTest {

    @Autowired
    private lateinit var authorDaoJdbc: AuthorDaoJdbc

    @Test
    fun `success add author`() {
        val expectedAuthor = AuthorModel(authorName = "автор")
        authorDaoJdbc.insertIfNotExists(expectedAuthor)
        val actualAuthor = authorDaoJdbc.getByName("автор")
        assertEquals(expectedAuthor.authorName, actualAuthor.authorName)
    }

    @Test
    fun `success get all authors`() {
        val expectAuthor = AuthorModel(authorName = "автор")
        authorDaoJdbc.insertIfNotExists(expectAuthor)
        val authors = authorDaoJdbc.getAll()
        assertTrue(authors.size == 2)
        assertTrue(authors.any { it.authorName == expectAuthor.authorName })
    }

    @Test
    fun `success delete author`() {
        val authorForDelete = AuthorModel(authorName = "автор")
        authorDaoJdbc.insertIfNotExists(authorForDelete)
        val expectedAuthor = authorDaoJdbc.getByName(authorForDelete.authorName)
        authorDaoJdbc.deleteById(expectedAuthor.id)
        val actualAuthors = authorDaoJdbc.getAll()
        assertTrue(actualAuthors.none { it == expectedAuthor })
    }

    @Test
    fun `success get author`() {
        val expectedAuthor = AuthorModel(id = 2, authorName = "автор")
        authorDaoJdbc.insertIfNotExists(expectedAuthor)
        val authorByName = authorDaoJdbc.getByName(expectedAuthor.authorName)
        val authorById = authorDaoJdbc.getById(authorByName.id)
        assertEquals(authorByName, authorById)
    }
}