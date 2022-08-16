package ru.lpfun.spring.homework04.parser

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class QuestionsCsvParserImplTest {
    @Test
    fun `Question csv parser`() {
        val parser = QuestionsCsvParserImpl()
        val questions = parser.parse("data/questions.csv")

        assertTrue(questions.size == 5)
        val firstQuestion = questions.first()
        firstQuestion.run {
            assertNotNull(number)
            assertNotNull(question)
            assertNotNull(answerVariants)
            assertNotNull(correctAnswers)
        }
    }
}