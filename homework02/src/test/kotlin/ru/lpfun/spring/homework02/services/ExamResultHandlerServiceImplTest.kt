package ru.lpfun.spring.homework02.services

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.lpfun.spring.homework02.common.interfaces.ExamResultHandlerService
import ru.lpfun.spring.homework02.common.interfaces.io.IOService
import ru.lpfun.spring.homework02.common.model.ExamResult
import ru.lpfun.spring.homework02.common.model.Student
import kotlin.test.assertTrue

@ExtendWith(MockKExtension::class)
internal class ExamResultHandlerServiceImplTest {

    @MockK
    private lateinit var ioServiceMock: IOService

    private lateinit var examResultHandlerService: ExamResultHandlerService

    @BeforeEach
    fun setUp() {
        examResultHandlerService = ExamResultHandlerServiceImpl(ioServiceMock)
    }

    @Test
    fun `Print exam result`() {
        val stubStudent = Student("student name")
        val stubExamResult = ExamResult(10, 5, true)

        every { ioServiceMock.println(allAny()) } answers { println(args[0]) }

        examResultHandlerService.handleExamResult(stubStudent, stubExamResult)

        verify(exactly = 3) {
            ioServiceMock.println(allAny())
        }
    }

    @Test
    fun `Printed exam data`() {
        val student = Student("student name")
        val examResult = ExamResult(10, 5, true)

        val ioServiceMock = MockIOService()
        examResultHandlerService = ExamResultHandlerServiceImpl(ioServiceMock)

        examResultHandlerService.handleExamResult(student, examResult)

        ioServiceMock.outPutArr.run {
            assertTrue(last().contains(student.name))
            assertTrue(last().contains(examResult.trueAnswersCount.toString()))
            assertTrue(last().contains(examResult.questionsCount.toString()))
        }
    }
}

