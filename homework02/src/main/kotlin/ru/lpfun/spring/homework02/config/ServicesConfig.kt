package ru.lpfun.spring.homework02.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.lpfun.spring.homework02.common.interfaces.*
import ru.lpfun.spring.homework02.common.interfaces.io.IOService
import ru.lpfun.spring.homework02.services.*

@Configuration
open class ServicesConfig {

    @Bean
    open fun examService(
        authStudentService: IdentificationStudentService,
        examExecutorService: ExamExecutorService,
        examResultHandlerService: ExamResultHandlerService
    ): ExamService {
        return ExamServiceImpl(
            identificationStudentService = authStudentService,
            examExecutorService = examExecutorService,
            examResultHandlerService = examResultHandlerService
        )
    }

    @Bean
    open fun identificationStudentService(ioService: IOService): IdentificationStudentService {
        return IdentificationStudentServiceImpl(ioService)
    }

    @Bean
    open fun examExecutorService(
        ioService: IOService,
        questionDao: QuestionDao,
        @Value("\${exam.numOfCorrectAnswersToPassExam}") numOfCorrectAnswersToPassExam: Int
    ): ExamExecutorService {
        return ExamExecutorServiceImpl(ioService, questionDao, numOfCorrectAnswersToPassExam)
    }

    @Bean
    open fun examResultHandler(ioService: IOService): ExamResultHandlerService {
        return ExamResultHandlerServiceImpl(ioService)
    }

    @Bean
    open fun ioService(): IOService {
        return IOServiceImpl(System.`in`, System.out)
    }
}