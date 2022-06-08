package ru.lpfun.spring.homework02.common.interfaces

import ru.lpfun.spring.homework02.common.model.Question

interface QuestionDao {
    fun getQuestions(): List<Question>
}
