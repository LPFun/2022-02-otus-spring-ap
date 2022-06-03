package ru.lpfun.spring.homework02.dao

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.lpfun.spring.homework02.common.interfaces.Parser
import ru.lpfun.spring.homework02.parser.QuestionCsv

@ExtendWith(SpringExtension::class)
internal class QuestionDaoImplTest {

    @MockkBean
    private lateinit var parserMock: Parser<QuestionCsv>

    @Test
    fun `Parse questions`() {
        val path = "path"
        val questionDao = QuestionDaoImpl(path, parserMock)
        val questionCsv = QuestionCsv(
            "0",
            "question",
            "answer1 answer2",
            "answer1"
        )

        every { parserMock.parse(path) } returns listOf(questionCsv)

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
            parserMock.parse(path)
        }
    }
}