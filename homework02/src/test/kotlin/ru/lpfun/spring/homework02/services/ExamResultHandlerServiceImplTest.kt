package ru.lpfun.spring.homework02.services

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.lpfun.spring.homework02.common.interfaces.ExamResultHandlerService
import ru.lpfun.spring.homework02.common.interfaces.io.IOService
import ru.lpfun.spring.homework02.common.model.ExamResult
import ru.lpfun.spring.homework02.common.model.Student
import kotlin.test.assertTrue

@ExtendWith(SpringExtension::class)
internal class ExamResultHandlerServiceImplTest {

    @MockkBean
    private lateinit var ioServiceMock: IOService

    private lateinit var examResultHandlerService: ExamResultHandlerService

    @BeforeEach
    fun setUp() {
        examResultHandlerService = ExamResultHandlerServiceImpl(ioServiceMock)
    }

    @Test
    fun `Print exam result`() {
        val stubStudent = Student("student name")
        val stubExamResult = ExamResult(10, 5)

        every { ioServiceMock.println(allAny()) } answers { println(args[0]) }

        examResultHandlerService.handleExamResult(stubStudent, stubExamResult)

        verify(exactly = 2) {
            ioServiceMock.println(allAny())
        }
    }

    @Test
    fun `Printed exam data`() {
        val student = Student("student name")
        val examResult = ExamResult(10, 5)

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
