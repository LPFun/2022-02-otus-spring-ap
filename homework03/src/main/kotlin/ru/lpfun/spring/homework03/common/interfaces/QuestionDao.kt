package ru.lpfun.spring.homework03.common.interfaces

import ru.lpfun.spring.homework03.common.model.Question

interface QuestionDao {
    fun getQuestions(): List<Question>
}
