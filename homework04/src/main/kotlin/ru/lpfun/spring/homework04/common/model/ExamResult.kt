package ru.lpfun.spring.homework04.common.model

data class ExamResult(
    val questionsCount: Int,
    val trueAnswersCount: Int,
    val isExamPassed: Boolean
)