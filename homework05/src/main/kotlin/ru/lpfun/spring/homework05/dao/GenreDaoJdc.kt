package ru.lpfun.spring.homework05.dao

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import ru.lpfun.spring.homework05.common.dao.IGenreDao
import ru.lpfun.spring.homework05.common.models.GenreModel
import ru.lpfun.spring.homework05.dto.GenreDto
import java.sql.ResultSet

@Repository
class GenreDaoJdc(
    private val namedParameterJdbcOperations: NamedParameterJdbcOperations
) : IGenreDao {

    override fun insert(model: GenreModel): GenreModel {
        namedParameterJdbcOperations.update(
            "insert into genres (id, name) values (:id, :genre)",
            mapOf("id" to model.id, "genre" to model.genre)
        )
        return model
    }

    override fun getById(id: Long): GenreModel {
        return namedParameterJdbcOperations.queryForObject(
            "select id, name from genres where id=:id",
            mapOf("id" to id),
            GenreMapper()
        )?.toModel() ?: GenreModel()
    }

    override fun update(model: GenreModel): GenreModel {
        namedParameterJdbcOperations.update(
            "update genres set name=:genre where id=:id",
            mapOf("genre" to model.genre, "id" to model.id)
        )
        return model
    }

    override fun getAll(): List<GenreModel> {
        return namedParameterJdbcOperations.query(
            "select id, name from genres",
            GenreMapper()
        ).map { gdto ->
            gdto.toModel()
        }
    }

    override fun deleteById(id: Long) {
        namedParameterJdbcOperations.update(
            "delete from genres where id = :id",
            mapOf("id" to id)
        )
    }

    class GenreMapper : RowMapper<GenreDto> {
        override fun mapRow(rs: ResultSet, rowNum: Int): GenreDto {
            return GenreDto(
                id = rs.getLong("id"),
                genre = rs.getNString("name")
            )
        }
    }
}