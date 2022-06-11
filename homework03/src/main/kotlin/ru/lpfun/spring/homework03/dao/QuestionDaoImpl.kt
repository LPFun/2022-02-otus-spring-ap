package ru.lpfun.spring.homework03.dao

import org.springframework.stereotype.Service
import ru.lpfun.spring.homework03.common.interfaces.Parser
import ru.lpfun.spring.homework03.common.interfaces.QuestionDao
import ru.lpfun.spring.homework03.common.model.Question
import ru.lpfun.spring.homework03.config.ExamProps
import ru.lpfun.spring.homework03.parser.QuestionCsv

@Service
class QuestionDaoImpl(
    private val examProps: ExamProps,
    private val parser: Parser<QuestionCsv>
) : QuestionDao {

    override fun getQuestions(): List<Question> {
        val questions = parser.parse(examProps.filePath).map { q ->
            q.toModel()
        }
        return questions
    }
}
