package ru.lpfun.spring.homework02.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.lpfun.spring.homework02.common.interfaces.Parser
import ru.lpfun.spring.homework02.parser.QuestionCsv
import ru.lpfun.spring.homework02.parser.QuestionsCsvParserImpl

@Configuration
open class ParserConfig {

    @Bean
    open fun questionCsvParser(): Parser<QuestionCsv> {
        return QuestionsCsvParserImpl()
    }
}