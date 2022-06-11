package ru.lpfun.spring.homework03.common.model

data class ExamResult(
    val questionsCount: Int,
    val trueAnswersCount: Int,
    val isExamPassed: Boolean
)