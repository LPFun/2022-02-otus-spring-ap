package ru.lpfun.spring.homework03.services

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
import org.springframework.context.MessageSource
import ru.lpfun.spring.homework03.common.interfaces.ExamExecutorService
import ru.lpfun.spring.homework03.common.interfaces.QuestionDao
import ru.lpfun.spring.homework03.common.interfaces.io.IOService
import ru.lpfun.spring.homework03.common.model.Answer
import ru.lpfun.spring.homework03.common.model.Question
import ru.lpfun.spring.homework03.config.ExamProps

@ExtendWith(MockKExtension::class)
internal class ExamExecutorServiceImplTest {

    @MockK
    private lateinit var ioServiceMock: IOService

    @MockK
    private lateinit var questionDaoMock: QuestionDao

    @MockK
    private lateinit var props: ExamProps

    @MockK
    private lateinit var messageSource: MessageSource

    private lateinit var examExecutorService: ExamExecutorService

    @Test
    fun `Execute exam`() {
        examExecutorService = ExamExecutorServiceImpl(ioServiceMock, questionDaoMock, props, messageSource)
        val question = Question(
            id = "0",
            question = "question",
            answers = listOf(Answer("0", "answer")),
            correctAnswers = listOf(Answer("0", "answer"))
        )

        val instructionMsg = "Enter correct answer id"
        every { messageSource.getMessage(allAny(), allAny(), allAny()) } returns instructionMsg
        every { props.numOfCorrectAnswersToPassExam } returns 1
        every { questionDaoMock.getQuestions() } returns listOf(question)
        every { ioServiceMock.getInput() } returns "1"
        every { ioServiceMock.println(instructionMsg) } just Runs
        every { ioServiceMock.print(allAny()) } just Runs

        val examResult = examExecutorService.executeExam()

        assertEquals(1, examResult.questionsCount)
        assertEquals(1, examResult.trueAnswersCount)

        verify {
            messageSource.getMessage(allAny(), allAny(), allAny())
            props.numOfCorrectAnswersToPassExam
            questionDaoMock.getQuestions()
            ioServiceMock.println(instructionMsg)
            ioServiceMock.print(allAny())
            ioServiceMock.getInput()
        }
    }

    @Test
    fun `Execute exam with io check`() {
        val outPutArr = mutableListOf<String>()
        examExecutorService = ExamExecutorServiceImpl(ioServiceMock, questionDaoMock, props, messageSource)
        val question = Question(
            id = "0",
            question = "question",
            answers = listOf(Answer("0", "answer")),
            correctAnswers = listOf(Answer("0", "answer"))
        )

        val instructionMsg = "Enter correct answer id"
        every { messageSource.getMessage(allAny(), allAny(), allAny()) } returns instructionMsg
        every { ioServiceMock.getInput() } returns "1"
        every { ioServiceMock.println(instructionMsg) } just Runs
        every { ioServiceMock.print(capture(outPutArr)) } just Runs
        every { props.numOfCorrectAnswersToPassExam } returns 1
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
            messageSource.getMessage(allAny(), allAny(), allAny())
            questionDaoMock.getQuestions()
            ioServiceMock.getInput()
            ioServiceMock.println(allAny())
            ioServiceMock.print(allAny())
        }
    }
}