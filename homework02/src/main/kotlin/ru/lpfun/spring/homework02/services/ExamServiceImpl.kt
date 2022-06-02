package ru.lpfun.spring.homework02.services

import ru.lpfun.spring.homework02.common.interfaces.ExamExecutorService
import ru.lpfun.spring.homework02.common.interfaces.ExamResultHandlerService
import ru.lpfun.spring.homework02.common.interfaces.ExamService
import ru.lpfun.spring.homework02.common.interfaces.IdentificationService
import ru.lpfun.spring.homework02.common.model.Student

class ExamServiceImpl(
    private val identificationStudentService: IdentificationService<Student>,
    private val examExecutorService: ExamExecutorService,
    private val examResultHandlerService: ExamResultHandlerService
) : ExamService {
    override fun exam() {
        // спросить имя
        val student = identificationStudentService.identificate()
        // задать вопросы
        val examResult = examExecutorService.executeExam()
        // вывести результат
        examResultHandlerService.handleExamResult(student, examResult)
    }
}

