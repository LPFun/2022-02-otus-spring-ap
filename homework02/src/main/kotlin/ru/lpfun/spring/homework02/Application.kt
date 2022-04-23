package ru.lpfun.spring.homework02

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan
open class Application

fun main() {
    val appRunner = AppRunner(AnnotationConfigApplicationContext(Application::class.java))
    appRunner.run()
    appRunner.close()
}