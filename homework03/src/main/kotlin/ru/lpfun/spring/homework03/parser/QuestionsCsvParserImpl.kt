package ru.lpfun.spring.homework03.parser

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import ru.lpfun.spring.homework03.common.interfaces.Parser
import java.io.InputStreamReader
import java.util.*

@Service
class QuestionsCsvParserImpl : Parser<QuestionCsv> {
    override fun parse(path: String): List<QuestionCsv> {
        val csvFileExtension = ".csv"
        val localizedFilePath = "${path.removeSuffix(csvFileExtension)}_${Locale.getDefault()}$csvFileExtension"
        val csvResource = try {
            ClassPathResource(localizedFilePath, this.javaClass.classLoader)
        } catch (e: Exception) {
            e.printStackTrace()
            ClassPathResource(path, this.javaClass.classLoader)
        }
        val stream = csvResource.inputStream
        return parseCsv(InputStreamReader(stream))
    }
}


