package ru.lpfun.spring.homework04.common.interfaces

import ru.lpfun.spring.homework04.common.model.Question

interface QuestionDao {
    fun getQuestions(): List<Question>
}
