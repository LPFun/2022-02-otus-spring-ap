package ru.lpfun.spring.homework04

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.lpfun.spring.homework04.config.AppSettings
import ru.lpfun.spring.homework04.config.ExamProps

@SpringBootApplication
@EnableConfigurationProperties(ExamProps::class, AppSettings::class)
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
