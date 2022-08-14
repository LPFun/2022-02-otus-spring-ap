package ru.lpfun.spring.homework05.dao

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import ru.lpfun.spring.homework05.common.dao.IAuthorDao
import ru.lpfun.spring.homework05.common.dao.IBookDao
import ru.lpfun.spring.homework05.common.dao.IGenreDao
import ru.lpfun.spring.homework05.common.models.BookModel
import ru.lpfun.spring.homework05.dto.BookDto
import java.sql.ResultSet

@Repository
class BookDaoJdbc(
    private val namedParameterJdbcOperations: NamedParameterJdbcOperations,
    private val genreDao: IGenreDao,
    private val authorDao: IAuthorDao
) : IBookDao {
    override fun insert(model: BookModel): BookModel {
        genreDao.insert(model.genre)
        authorDao.insert(model.author)
        namedParameterJdbcOperations.update(
            "insert into books (id, title, author_id, genre_id) " +
                    "values (:id, :title, :author, :genre)",
            mapOf(
                "id" to model.id,
                "title" to model.title,
                "author" to model.author,
                "genre" to model.genre
            )
        )
        return model
    }

    override fun getById(id: Long): BookModel {
        val bookDto = namedParameterJdbcOperations.queryForObject(
            "select id, title, author_id, genre_id from authors where id=:id",
            mapOf("id" to id),
            BookMapper()
        )
        val authorModel = authorDao.getById(requireNotNull(bookDto?.authorId))
        val genreModel = genreDao.getById(requireNotNull(bookDto?.genreId))
        return bookDto?.toModel(
            author = authorModel,
            genre = genreModel
        ) ?: BookModel.NONE
    }

    override fun update(model: BookModel): BookModel {
        authorDao.update(model.author)
        genreDao.update(model.genre)
        namedParameterJdbcOperations.update(
            "update books set title=:title " +
                    "where id=:id",
            mapOf(
                "title" to model.title,
                "id" to model.id
            )
        )
        return model
    }

    override fun getAll(): List<BookModel> {
        val booksDto = namedParameterJdbcOperations.query(
            "select id, title, author_id, genre_id from books",
            BookMapper()
        )
        val authors = authorDao.getAll()
        val genres = genreDao.getAll()
        return booksDto.map { bdto ->
            bdto.toModel(
                author = authors.first { a -> a.id == bdto.authorId },
                genre = genres.first { g -> g.id == bdto.genreId }
            )
        }
    }

    override fun deleteById(id: Long) {
        namedParameterJdbcOperations.update(
            "delete from genres where id=:id",
            mapOf("id" to id)
        )
    }

    class BookMapper : RowMapper<BookDto> {
        override fun mapRow(rs: ResultSet, rowNum: Int): BookDto? {
            return BookDto(
                id = rs.getLong("id"),
                title = rs.getString("title"),
                authorId = rs.getLong("author_id"),
                genreId = rs.getLong("genre_id")
            )
        }

    }
}