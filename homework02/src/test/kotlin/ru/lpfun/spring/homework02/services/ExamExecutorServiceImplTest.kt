package ru.lpfun.spring.homework02.services

import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.lpfun.spring.homework02.common.interfaces.ExamExecutorService
import ru.lpfun.spring.homework02.common.interfaces.QuestionDao
import ru.lpfun.spring.homework02.common.interfaces.io.IOService
import ru.lpfun.spring.homework02.common.model.Answer
import ru.lpfun.spring.homework02.common.model.Question

@ExtendWith(MockKExtension::class)
internal class ExamExecutorServiceImplTest {

    @MockK
    private lateinit var ioServiceMock: IOService

    @MockK
    private lateinit var questionDaoMock: QuestionDao

    private lateinit var examExecutorService: ExamExecutorService

    @Test
    fun `Execute exam`() {
        examExecutorService = ExamExecutorServiceImpl(ioServiceMock, questionDaoMock, 1)
        val question = Question(
            id = "0",
            question = "question",
            answers = listOf(Answer("0", "answer")),
            correctAnswers = listOf(Answer("0", "answer"))
        )
        every { questionDaoMock.getQuestions() } returns listOf(question)
        every { ioServiceMock.getInput() } returns "1"
        every { ioServiceMock.println(allAny()) } just Runs
        every { ioServiceMock.print(allAny()) } just Runs

        val examResult = examExecutorService.executeExam()

        assertEquals(1, examResult.questionsCount)
        assertEquals(1, examResult.trueAnswersCount)

        verify {
            questionDaoMock.getQuestions()
            ioServiceMock.println(allAny())
            ioServiceMock.print(allAny())
            ioServiceMock.getInput()
        }
    }

    @Test
    fun `Execute exame with io check`() {
        val mockIOService = MockIOService()
        mockIOService.inputArr.add("1")
        examExecutorService = ExamExecutorServiceImpl(mockIOService, questionDaoMock, 1)
        val question = Question(
            id = "0",
            question = "question",
            answers = listOf(Answer("0", "answer")),
            correctAnswers = listOf(Answer("0", "answer"))
        )

        every { questionDaoMock.getQuestions() } returns listOf(question)

        val examResult = examExecutorService.executeExam()

        mockIOService.outPutArr.run {
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
        }
    }
}