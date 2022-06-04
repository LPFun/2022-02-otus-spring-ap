package ru.lpfun.spring.homework02.services

import ru.lpfun.spring.homework02.common.interfaces.ExamResultHandlerService
import ru.lpfun.spring.homework02.common.interfaces.io.IOService
import ru.lpfun.spring.homework02.common.model.ExamResult
import ru.lpfun.spring.homework02.common.model.Student

class ExamResultHandlerServiceImpl(
    private val ioService: IOService
) : ExamResultHandlerService {
    override fun handleExamResult(student: Student, examResult: ExamResult) {
        ioService.println("Exam result:")
        ioService.println("Exam ${if (examResult.isExamPassed) "passed" else "not passed"}")
        ioService.println("${student.name} answered correctly to ${examResult.trueAnswersCount} questions from ${examResult.questionsCount}")
    }
}