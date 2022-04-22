package ru.lpfun.homework01

import org.junit.jupiter.api.Test
import ru.lpfun.spring.homework01.dao.QuestionDaoImpl
import ru.lpfun.spring.homework01.parser.QuestionsCsvParserImpl
import ru.lpfun.spring.homework01.services.QuestionPrintServiceImpl
import kotlin.test.assertEquals

class Homework01ApplicationTests {
    @Test
    fun `test question and answers parse from csv`() {
        val parseService = QuestionDaoImpl(
            path = "data/questions.csv",
            parser = QuestionsCsvParserImpl()
        )
        val questions = parseService.getQuestions()
        val questionList = questions.questionList
        assertEquals(5, questionList.size)
        questionList.forEach { q ->
            assertEquals(4, q.answers.size)
            assertEquals(1, q.correctAnswers.size)
        }
        val printer = QuestionPrintServiceImpl()
        printer.print(questions)
    }
}
