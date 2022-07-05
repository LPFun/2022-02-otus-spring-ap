package ru.lpfun.spring.homework03.parser

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import ru.lpfun.spring.homework03.common.interfaces.Parser
import java.io.InputStreamReader

@Service
class QuestionsCsvParserImpl : Parser<QuestionCsv> {
    override fun parse(path: String): List<QuestionCsv> {
        val csvResource = ClassPathResource(path, this.javaClass.classLoader)
        val stream = csvResource.inputStream
        return parseCsv(InputStreamReader(stream))
    }
}


