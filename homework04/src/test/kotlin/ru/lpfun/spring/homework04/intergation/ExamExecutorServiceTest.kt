package ru.lpfun.spring.homework04.intergation

import com.ninjasquad.springmockk.MockkBean
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.lpfun.spring.homework04.common.interfaces.ExamExecutorService
import ru.lpfun.spring.homework04.common.interfaces.MsgPrinter
import ru.lpfun.spring.homework04.common.interfaces.QuestionDao
import ru.lpfun.spring.homework04.common.interfaces.io.IOService
import ru.lpfun.spring.homework04.common.model.Answer
import ru.lpfun.spring.homework04.common.model.Question
import ru.lpfun.spring.homework04.config.ExamProps
import ru.lpfun.spring.homework04.services.ExamExecutorServiceImpl

@SpringBootTest(classes = [ExamExecutorServiceImpl::class])
@ExtendWith(SpringExtension::class)
internal class ExamExecutorServiceTest {

    @MockkBean
    private lateinit var ioServiceMock: IOService

    @MockkBean
    private lateinit var questionDaoMock: QuestionDao

    @MockkBean
    private lateinit var props: ExamProps

    @MockkBean
    private lateinit var msgPrinter: MsgPrinter

    @Autowired
    private lateinit var examExecutorService: ExamExecutorService

    @BeforeEach
    private fun setUp() {
        val question = Question(
            id = "0",
            question = "question",
            answers = listOf(Answer("0", "answer")),
            correctAnswers = listOf(Answer("0", "answer"))
        )
        every { props.numOfCorrectAnswersToPassExam } returns 3
        every { questionDaoMock.getQuestions() } returns listOf(question)
        every { msgPrinter.printlnMsg(any()) } just runs
        every { ioServiceMock.getInput() } returns "1"
//        every { props.lang } returns "en"
    }

    @Test
    fun `execute exam on eng locale`() {
        every { ioServiceMock.print(any()) } just runs
        val examResult = examExecutorService.executeExam()

        assertEquals(1, examResult.questionsCount)
        assertEquals(1, examResult.trueAnswersCount)

        verify {
            props.numOfCorrectAnswersToPassExam
            questionDaoMock.getQuestions()
            ioServiceMock.print(any())
            ioServiceMock.getInput()
        }
    }

    @Test
    fun `execute exam with io check`() {
        val outPutArr = mutableListOf<String>()
        every { ioServiceMock.print(capture(outPutArr)) } just Runs

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
            props.numOfCorrectAnswersToPassExam
            questionDaoMock.getQuestions()
            ioServiceMock.print(any())
            ioServiceMock.getInput()
        }
    }
}