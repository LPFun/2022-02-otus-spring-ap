package ru.lpfun.spring.homework01.common.model

data class Question(
    val id: String = "",
    val question: String = "",
    val answers: List<Answer> = listOf(),
    val correctAnswers: List<Answer> = listOf(),
)

