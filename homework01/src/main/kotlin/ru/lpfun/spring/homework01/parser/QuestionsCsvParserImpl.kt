package ru.lpfun.spring.homework01.parser

import org.springframework.core.io.ClassPathResource
import ru.lpfun.spring.homework01.common.interfaces.Parser
import java.io.InputStreamReader

class QuestionsCsvParserImpl : Parser<QuestionCsv> {
    override fun parse(path: String): List<QuestionCsv> {
        val csvResource = ClassPathResource(path, this.javaClass.classLoader)
        val stream = csvResource.inputStream
        return parseCsv(InputStreamReader(stream))
    }
}


