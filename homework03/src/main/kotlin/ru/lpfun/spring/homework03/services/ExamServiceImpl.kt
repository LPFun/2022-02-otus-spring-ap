package ru.lpfun.spring.homework03.services

import org.springframework.stereotype.Service
import ru.lpfun.spring.homework03.common.interfaces.ExamExecutorService
import ru.lpfun.spring.homework03.common.interfaces.ExamResultHandlerService
import ru.lpfun.spring.homework03.common.interfaces.ExamService
import ru.lpfun.spring.homework03.common.interfaces.IdentificationStudentService

@Service
class ExamServiceImpl(
    private val identificationStudentService: IdentificationStudentService,
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

