package ru.lpfun.spring.homework02.common.interfaces

import ru.lpfun.spring.homework02.common.model.Questions

interface QuestionPrintService {
    fun print(questions: Questions)
}
