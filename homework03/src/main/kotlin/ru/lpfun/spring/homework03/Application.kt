package ru.lpfun.spring.homework03

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.lpfun.spring.homework03.config.ExamProps

@SpringBootApplication
@EnableConfigurationProperties(ExamProps::class)
class Application

fun main(args: Array<String>) {
	val context = runApplication<Application>(*args)
	AppRunner(context).run()
}
