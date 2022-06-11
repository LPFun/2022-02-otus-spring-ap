package ru.lpfun.spring.homework03

import org.springframework.context.ConfigurableApplicationContext
import ru.lpfun.spring.homework03.services.ExamServiceImpl

class AppRunner(
    private val context: ConfigurableApplicationContext
) {
    fun run() {
        val examService = context.getBean(ExamServiceImpl::class.java)
        examService.exam()
    }

    fun close() {
        context.close()
    }
}
