package ru.lpfun.spring.homework03.providers

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.lpfun.spring.homework03.common.interfaces.ExamFilePathProvider
import ru.lpfun.spring.homework03.config.ExamProps
import java.util.*

@Component
class ExamFilePathProviderImpl(
    @Value("\${app.settings.lang}")
    private val lang: String,
    private val examProps: ExamProps,
) : ExamFilePathProvider {
    override fun path(): String {
        val csvFileExtension = ".csv"
        return "${examProps.filePath.removeSuffix(csvFileExtension)}_${Locale(lang)}$csvFileExtension"
    }
}