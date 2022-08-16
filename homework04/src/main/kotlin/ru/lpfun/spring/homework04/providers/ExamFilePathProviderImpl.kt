package ru.lpfun.spring.homework04.providers

import org.springframework.stereotype.Component
import ru.lpfun.spring.homework04.common.interfaces.ExamFilePathProvider
import ru.lpfun.spring.homework04.config.AppSettings
import ru.lpfun.spring.homework04.config.ExamProps
import java.util.*

@Component
class ExamFilePathProviderImpl(
    private val appSettings: AppSettings,
    private val examProps: ExamProps,
) : ExamFilePathProvider {
    override fun path(): String {
        val csvFileExtension = ".csv"
        return "${examProps.filePath.removeSuffix(csvFileExtension)}_${Locale(appSettings.lang)}$csvFileExtension"
    }
}