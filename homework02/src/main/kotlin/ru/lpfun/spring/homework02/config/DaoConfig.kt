package ru.lpfun.spring.homework02.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.lpfun.spring.homework02.common.interfaces.Parser
import ru.lpfun.spring.homework02.common.interfaces.QuestionDao
import ru.lpfun.spring.homework02.dao.QuestionDaoImpl
import ru.lpfun.spring.homework02.parser.QuestionCsv

@Configuration
open class DaoConfig {

    @Bean
    open fun questionDao(
        parser: Parser<QuestionCsv>,
        @Value("\${exam.testfile}") path: String
    ): QuestionDao {
        return QuestionDaoImpl(path, parser)
    }
}