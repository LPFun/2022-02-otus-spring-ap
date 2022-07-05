package ru.lpfun.spring.homework03.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "exam")
data class ExamProps(
    val filePath: String,
    val numOfCorrectAnswersToPassExam: Int,
    val lang: String
)