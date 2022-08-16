package ru.lpfun.spring.homework04.intergation

import com.ninjasquad.springmockk.MockkBean
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.lpfun.spring.homework04.common.interfaces.ExamResultHandlerService
import ru.lpfun.spring.homework04.common.interfaces.MsgPrinter
import ru.lpfun.spring.homework04.common.interfaces.MsgProvider
import ru.lpfun.spring.homework04.common.model.ExamResult
import ru.lpfun.spring.homework04.common.model.Student
import ru.lpfun.spring.homework04.services.ExamResultHandlerServiceImpl

@SpringBootTest(classes = [ExamResultHandlerServiceImpl::class])
@ExtendWith(SpringExtension::class)
internal class ExamResultHandlerServiceTest {

    @MockkBean
    private lateinit var msgPrinter: MsgPrinter

    @MockkBean
    private lateinit var msgProvider: MsgProvider

    @Autowired
    private lateinit var examResultHandlerService: ExamResultHandlerService

    @Test
    fun `print exam result`() {
        val stubStudent = Student("student name")
        val stubExamResult = ExamResult(10, 5, true)

        every { msgPrinter.printlnMsg(any(), any()) } just runs
        every { msgProvider.getMsg(any()) } answers { firstArg() }

        examResultHandlerService.handleExamResult(stubStudent, stubExamResult)

        verify {
            msgPrinter.printlnMsg(any(), any())
            msgProvider.getMsg(any())
        }
    }

    @Test
    fun `printed exam data`() {
        val student = Student("student name")
        val examResult = ExamResult(10, 5, true)
        val outPutArr = mutableListOf<Array<out Any>>()

        every { msgPrinter.printlnMsg(any(), capture(outPutArr)) } just Runs
        every { msgPrinter.printlnMsg(any()) } just Runs
        every { msgProvider.getMsg(any()) } answers { firstArg() }

        examResultHandlerService.handleExamResult(student, examResult)

        outPutArr.joinToString { it.joinToString() }.run {
            assertTrue(contains(student.name))
            assertTrue(contains(examResult.trueAnswersCount.toString()))
            assertTrue(contains(examResult.questionsCount.toString()))
        }

        verify {
            msgPrinter.printlnMsg(any(), any())
            msgPrinter.printlnMsg(any())
            msgProvider.getMsg(any())
        }
    }
}

