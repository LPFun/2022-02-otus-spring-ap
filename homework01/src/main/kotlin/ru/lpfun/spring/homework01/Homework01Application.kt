package ru.lpfun.spring.homework01

import org.springframework.context.support.ClassPathXmlApplicationContext

fun main(args: Array<String>) {
    val appRunner = AppRunner(ClassPathXmlApplicationContext("/spring-context.xml"))
    appRunner.run()
    appRunner.close()
}
