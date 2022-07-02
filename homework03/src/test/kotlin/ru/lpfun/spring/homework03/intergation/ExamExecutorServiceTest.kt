package ru.lpfun.spring.homework03.intergation

import com.ninjasquad.springmockk.MockkBean
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.MessageSource
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.lpfun.spring.homework03.common.interfaces.QuestionDao
import ru.lpfun.spring.homework03.common.interfaces.io.IOService
import ru.lpfun.spring.homework03.common.model.Answer
import ru.lpfun.spring.homework03.common.model.Question
import ru.lpfun.spring.homework03.config.ExamProps
import ru.lpfun.spring.homework03.services.ExamExecutorServiceImpl
import java.util.*

@SpringBootTest
@ExtendWith(SpringExtension::class)
internal class ExamExecutorServiceTest {

    @MockkBean
    private lateinit var ioServiceMock: IOService

    @MockkBean
    private lateinit var questionDaoMock: QuestionDao

    @Autowired
    private lateinit var props: ExamProps

    @Autowired
    private lateinit var messageSource: MessageSource

    @Test
    fun `execute exam on eng locale`() {
        Locale.setDefault(Locale.ENGLISH)
        val examExecutorService = ExamExecutorServiceImpl(ioServiceMock, questionDaoMock, props, messageSource)
        val question = Question(
            id = "0",
            question = "question",
            answers = listOf(Answer("0", "answer")),
            correctAnswers = listOf(Answer("0", "answer"))
        )

        val instructionMsg = "Enter correct answer id"
        every { questionDaoMock.getQuestions() } returns listOf(question)
        every { ioServiceMock.getInput() } returns "1"
        every { ioServiceMock.println(instructionMsg) } just Runs
        every { ioServiceMock.print(allAny()) } just Runs

        val examResult = examExecutorService.executeExam()

        assertEquals(1, examResult.questionsCount)
        assertEquals(1, examResult.trueAnswersCount)

        verify {
            questionDaoMock.getQuestions()
            ioServiceMock.println(instructionMsg)
            ioServiceMock.print(allAny())
            ioServiceMock.getInput()
        }
    }

    @Test
    fun `execute exam with io check on eng locale`() {
        Locale.setDefault(Locale.ENGLISH)
        val outPutArr = mutableListOf<String>()
        val examExecutorService = ExamExecutorServiceImpl(ioServiceMock, questionDaoMock, props, messageSource)
        val question = Question(
            id = "0",
            question = "question",
            answers = listOf(Answer("0", "answer")),
            correctAnswers = listOf(Answer("0", "answer"))
        )

        val instructionMsg = "Enter correct answer id"
        every { ioServiceMock.getInput() } returns "1"
        every { ioServiceMock.println(instructionMsg) } just Runs
        every { ioServiceMock.print(capture(outPutArr)) } just Runs
        every { questionDaoMock.getQuestions() } returns listOf(question)

        val examResult = examExecutorService.executeExam()

        outPutArr.run {
            assertTrue {
                isNotEmpty()
                any { it.contains("question") }
                any { it.contains("answer") }
            }
        }
        assertEquals(1, examResult.questionsCount)
        assertEquals(1, examResult.trueAnswersCount)

        verify {
            questionDaoMock.getQuestions()
            ioServiceMock.getInput()
            ioServiceMock.println(allAny())
            ioServiceMock.print(allAny())
        }
    }
}