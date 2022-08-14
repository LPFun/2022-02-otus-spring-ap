package ru.lpfun.spring.homework05.dao

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import ru.lpfun.spring.homework05.common.dao.IBookDao
import ru.lpfun.spring.homework05.common.models.BookModel
import ru.lpfun.spring.homework05.dto.AuthorDto
import ru.lpfun.spring.homework05.dto.BookDto
import ru.lpfun.spring.homework05.dto.GenreDto
import java.sql.ResultSet

@Repository
class BookDaoJdbc(
    private val namedParameterJdbcOperations: NamedParameterJdbcOperations
) : IBookDao {
    override fun insertIfNotExists(model: BookModel): BookModel {
        namedParameterJdbcOperations.update(
            "insert into books (id, title, author_id, genre_id) " +
                    "values (" +
                    ":id, " +
                    ":title, " +
                    "(select id from authors where name=:author), " +
                    "(select id from genres where name=:genre)" +
                    ")",
            mapOf(
                "id" to model.id,
                "title" to model.title,
                "author" to model.author.authorName,
                "genre" to model.genre.genreName
            )
        )
        return model
    }

    override fun getById(id: Long): BookModel {
        val bookDto = namedParameterJdbcOperations.queryForObject(
            "select books.id, title, authors.id, authors.name, genres.id, genres.name " +
                    "from books " +
                    "inner join authors on authors.id=books.author_id " +
                    "inner join genres on books.genre_id=genres.id " +
                    "where books.id=:id",
            mapOf("id" to id),
            BookMapper()
        )
        return bookDto?.toModel() ?: BookModel.NONE
    }

    override fun update(model: BookModel): BookModel {
        namedParameterJdbcOperations.update(
            "update books set title=:title, " +
                    "author_id=(select id from authors where name=:authorName), " +
                    "genre_id=(select id from genres where name=:genreName) " +
                    "where id=:id",
            mapOf(
                "title" to model.title,
                "id" to model.id,
                "authorName" to model.author.authorName,
                "genreName" to model.genre.genreName
            )
        )
        return model
    }

    override fun getAll(): List<BookModel> {
        val booksDto = namedParameterJdbcOperations.query(
            "select books.id, title, authors.id, authors.name, genres.id, genres.name " +
                    "from books " +
                    "inner join authors on authors.id=books.author_id " +
                    "inner join genres on books.genre_id=genres.id",
            BookMapper()
        )

        return booksDto.map { bdto ->
            bdto.toModel()
        }
    }

    override fun deleteById(id: Long) {
        namedParameterJdbcOperations.update(
            "delete from books where id=:id",
            mapOf("id" to id)
        )
    }

    class BookMapper : RowMapper<BookDto> {
        override fun mapRow(rs: ResultSet, rowNum: Int): BookDto? {
            return BookDto(
                id = rs.getLong("books.id"),
                title = rs.getString("title"),
                author = AuthorDto(
                    id = rs.getLong("authors.id"),
                    author = rs.getString("authors.name")
                ),
                genre = GenreDto(
                    id = rs.getLong("genres.id"),
                    genre = rs.getString("genres.name")
                )
            )
        }
    }
}