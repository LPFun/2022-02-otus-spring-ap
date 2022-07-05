package ru.lpfun.spring.homework03.providers

import org.springframework.stereotype.Service
import ru.lpfun.spring.homework03.common.interfaces.ExamFilePathProvider
import ru.lpfun.spring.homework03.config.ExamProps
import java.util.*

@Service
class ExamFilePathProviderImpl(
    private val examProps: ExamProps
) : ExamFilePathProvider {
    override fun path(): String {
        val csvFileExtension = ".csv"
        return "${examProps.filePath.removeSuffix(csvFileExtension)}_${Locale(examProps.lang)}$csvFileExtension"
    }
}