package ru.lpfun.spring.homework05.dao

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import ru.lpfun.spring.homework05.common.dao.IAuthorDao
import ru.lpfun.spring.homework05.common.models.AuthorModel
import ru.lpfun.spring.homework05.dto.AuthorDto
import java.sql.ResultSet

@Repository
class AuthorDaoJdbc(
    private val namedParameterJdbcOperations: NamedParameterJdbcOperations
) : IAuthorDao {
    override fun insertIfNotExists(model: AuthorModel): AuthorModel {
        val existsAuthor = try {
            namedParameterJdbcOperations.queryForObject(
                "select id, name from authors where name=:author",
                mapOf("author" to model.authorName),
                AuthorMapper()
            )
        } catch (e: EmptyResultDataAccessException) {
            AuthorDto()
        }
        if (existsAuthor?.author != model.authorName)
            namedParameterJdbcOperations.update(
                "insert into authors (name) values (:author)",
                mapOf("id" to model.id, "author" to model.authorName, "authorName" to model.authorName)
            )
        return model
    }

    override fun getById(id: Long): AuthorModel {
        return namedParameterJdbcOperations.queryForObject(
            "select id, name from authors where id=:id",
            mapOf("id" to id),
            AuthorMapper()
        )?.toModel() ?: AuthorModel()
    }

    override fun update(model: AuthorModel): AuthorModel {
        namedParameterJdbcOperations.update(
            "update authors set name=:author where id=:id",
            mapOf("author" to model.authorName, "id" to model.id)
        )
        return model
    }

    override fun getByName(name: String): AuthorModel {
        return namedParameterJdbcOperations.queryForObject(
            "select id, name from authors where name=:name",
            mapOf("name" to name),
            AuthorMapper()
        )?.toModel() ?: AuthorModel.NONE
    }

    override fun getAll(): List<AuthorModel> {
        return namedParameterJdbcOperations.query(
            "select id, name from authors",
            AuthorMapper()
        ).map { adto ->
            adto.toModel()
        }
    }

    override fun deleteById(id: Long) {
        namedParameterJdbcOperations.update(
            "delete from authors where id = :id",
            mapOf("id" to id)
        )
    }

    class AuthorMapper : RowMapper<AuthorDto> {
        override fun mapRow(rs: ResultSet, rowNum: Int): AuthorDto? {
            return AuthorDto(
                id = rs.getLong("id"),
                author = rs.getString("name")
            )
        }
    }
}