package ru.lpfun.spring.homework02.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.lpfun.spring.homework02.common.interfaces.ExamService
import ru.lpfun.spring.homework02.common.interfaces.QuestionDao
import ru.lpfun.spring.homework02.common.interfaces.QuestionPrintService
import ru.lpfun.spring.homework02.services.ExamServiceImpl
import ru.lpfun.spring.homework02.services.QuestionPrintServiceImpl

@Configuration
open class ServicesConfig {

    @Bean
    open fun questionPrintService(): QuestionPrintService {
        return QuestionPrintServiceImpl()
    }

    @Bean
    open fun examService(
        questionDao: QuestionDao,
        questionPrintService: QuestionPrintService
    ): ExamService {
        return ExamServiceImpl(questionDao, questionPrintService)
    }
}