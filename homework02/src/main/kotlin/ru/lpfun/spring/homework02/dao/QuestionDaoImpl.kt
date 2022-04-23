package ru.lpfun.spring.homework02.dao

import ru.lpfun.spring.homework02.common.interfaces.Parser
import ru.lpfun.spring.homework02.common.interfaces.QuestionDao
import ru.lpfun.spring.homework02.common.model.Questions
import ru.lpfun.spring.homework02.parser.QuestionCsv

class QuestionDaoImpl(
    private val path: String,
    private val parser: Parser<QuestionCsv>
) : QuestionDao {
    override fun getQuestions(): Questions {
        val questions = parser.parse(path).map { q ->
            q.toModel()
        }
        return Questions(questionList = questions)
    }
}
