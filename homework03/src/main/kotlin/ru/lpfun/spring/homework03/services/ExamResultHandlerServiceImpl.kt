package ru.lpfun.spring.homework03.services

import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import ru.lpfun.spring.homework03.common.interfaces.ExamResultHandlerService
import ru.lpfun.spring.homework03.common.interfaces.io.IOService
import ru.lpfun.spring.homework03.common.model.ExamResult
import ru.lpfun.spring.homework03.common.model.Student
import java.util.*

@Service
class ExamResultHandlerServiceImpl(
    private val ioService: IOService,
    private val messageSource: MessageSource
) : ExamResultHandlerService {
    override fun handleExamResult(student: Student, examResult: ExamResult) {
        ioService.run {
            println(messageSource.getMessage("result.exam-result", null, Locale.getDefault()))
            val passedExamText = messageSource.getMessage(
                if (examResult.isExamPassed) "result.passed" else "result.not-passed",
                null,
                Locale.getDefault()
            )
            println(messageSource.getMessage("result.exam", arrayOf(passedExamText), Locale.getDefault()))
            println(
                messageSource.getMessage(
                    "result.detailed-result",
                    arrayOf(student.name, examResult.trueAnswersCount, examResult.questionsCount),
                    Locale.getDefault()
                )
            )
        }
    }
}