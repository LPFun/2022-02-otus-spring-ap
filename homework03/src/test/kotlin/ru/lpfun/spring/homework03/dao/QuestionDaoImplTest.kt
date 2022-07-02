package ru.lpfun.spring.homework03.dao

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.lpfun.spring.homework03.common.interfaces.Parser
import ru.lpfun.spring.homework03.config.ExamProps
import ru.lpfun.spring.homework03.parser.QuestionCsv

@ExtendWith(MockKExtension::class)
internal class QuestionDaoImplTest {

    @MockK
    private lateinit var parserMock: Parser<QuestionCsv>

    @MockK
    private lateinit var propsMock: ExamProps

    @Test
    fun `Parse questions`() {
        val questionDao = QuestionDaoImpl(propsMock, parserMock)
        val questionCsv = QuestionCsv(
            "0",
            "question",
            "answer1 answer2",
            "answer1"
        )

        val pathMock = "path"
        every { propsMock.filePath } returns pathMock
        every { parserMock.parse(pathMock) } returns listOf(questionCsv)

        val question = questionDao.getQuestions().first()

        assertEquals(questionCsv.number, question.id)
        assertEquals(questionCsv.question, question.question)
        question.answers.forEach {
            assertTrue(questionCsv.answerVariants?.contains(it.answer)!!)

        }
        question.correctAnswers.forEach {
            assertTrue(questionCsv.correctAnswers?.contains(it.answer)!!)
        }

        verify {
            propsMock.filePath
            parserMock.parse(pathMock)
        }
    }
}