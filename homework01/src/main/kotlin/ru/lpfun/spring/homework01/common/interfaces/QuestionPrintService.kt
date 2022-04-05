package ru.lpfun.spring.homework01.common.interfaces

import ru.lpfun.spring.homework01.common.model.Questions

interface QuestionPrintService {
    fun print(questions: Questions)
}
