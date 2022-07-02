package ru.lpfun.spring.homework03.intergation

import com.ninjasquad.springmockk.MockkBean
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.MessageSource
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.lpfun.spring.homework03.common.interfaces.ExamResultHandlerService
import ru.lpfun.spring.homework03.common.interfaces.io.IOService
import ru.lpfun.spring.homework03.common.model.ExamResult
import ru.lpfun.spring.homework03.common.model.Student
import ru.lpfun.spring.homework03.services.ExamResultHandlerServiceImpl

@SpringBootTest
@ExtendWith(SpringExtension::class)
internal class ExamResultHandlerServiceTest {

    @MockkBean
    private lateinit var ioServiceMock: IOService

    @Autowired
    private lateinit var messageSource: MessageSource

    private lateinit var examResultHandlerService: ExamResultHandlerService

    @BeforeEach
    fun setUp() {
        examResultHandlerService = ExamResultHandlerServiceImpl(ioServiceMock, messageSource)
    }

    @Test
    fun `print exam result`() {
        val stubStudent = Student("student name")
        val stubExamResult = ExamResult(10, 5, true)

        every { ioServiceMock.println(allAny()) } answers { println(args[0]) }

        examResultHandlerService.handleExamResult(stubStudent, stubExamResult)

        verify(atLeast = 3) {
            ioServiceMock.println(allAny())
        }
    }

    @Test
    fun `printed exam data`() {
        val student = Student("student name")
        val examResult = ExamResult(10, 5, true)
        val outPutArr = mutableListOf<String>()

        every { ioServiceMock.println(capture(outPutArr)) } just Runs

        examResultHandlerService = ExamResultHandlerServiceImpl(ioServiceMock, messageSource)

        examResultHandlerService.handleExamResult(student, examResult)

        outPutArr.run {
            assertTrue(last().contains(student.name))
            assertTrue(last().contains(examResult.trueAnswersCount.toString()))
            assertTrue(last().contains(examResult.questionsCount.toString()))
        }

        verify(atLeast = 3) {
            ioServiceMock.println(any())
        }
    }
}

