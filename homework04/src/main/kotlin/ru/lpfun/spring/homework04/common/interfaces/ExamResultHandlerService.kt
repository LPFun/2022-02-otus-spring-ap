package ru.lpfun.spring.homework04.common.interfaces

import ru.lpfun.spring.homework04.common.model.ExamResult
import ru.lpfun.spring.homework04.common.model.Student

interface ExamResultHandlerService {
    fun handleExamResult(student: Student, examResult: ExamResult)
}