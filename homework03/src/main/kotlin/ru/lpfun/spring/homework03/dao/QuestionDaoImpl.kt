package ru.lpfun.spring.homework03.dao

import org.springframework.stereotype.Service
import ru.lpfun.spring.homework03.common.interfaces.ExamFilePathProvider
import ru.lpfun.spring.homework03.common.interfaces.Parser
import ru.lpfun.spring.homework03.common.interfaces.QuestionDao
import ru.lpfun.spring.homework03.common.model.Question
import ru.lpfun.spring.homework03.parser.QuestionCsv

@Service
class QuestionDaoImpl(
    private val filePathProvider: ExamFilePathProvider,
    private val parser: Parser<QuestionCsv>
) : QuestionDao {

    override fun getQuestions(): List<Question> {
        val questions = parser.parse(filePathProvider.path()).map { q ->
            q.toModel()
        }
        return questions
    }
}
