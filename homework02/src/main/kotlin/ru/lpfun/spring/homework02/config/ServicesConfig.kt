package ru.lpfun.spring.homework02.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.lpfun.spring.homework02.common.interfaces.ExamService
import ru.lpfun.spring.homework02.common.interfaces.IOService
import ru.lpfun.spring.homework02.common.interfaces.QuestionDao
import ru.lpfun.spring.homework02.services.ExamServiceImpl
import ru.lpfun.spring.homework02.services.IOServiceImpl

@Configuration
open class ServicesConfig {

    @Bean
    open fun examService(
        questionDao: QuestionDao,
        questionPrintService: IOService
    ): ExamService {
        return ExamServiceImpl(questionDao, questionPrintService)
    }

    @Bean
    open fun ioService(): IOService {
        return IOServiceImpl(System.`in`, System.out)
    }
}