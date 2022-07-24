package ru.lpfun.spring.homework03

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import ru.lpfun.spring.homework03.common.interfaces.ExamService

@Component
class AppRunner(
    private val examService: ExamService
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        examService.exam()
    }
}
