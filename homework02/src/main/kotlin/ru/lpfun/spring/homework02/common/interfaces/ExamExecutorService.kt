package ru.lpfun.spring.homework02.common.interfaces

import ru.lpfun.spring.homework02.common.model.ExamResult
import ru.lpfun.spring.homework02.common.model.Student

interface ExamExecutorService {
    fun executeExam(student: Student): ExamResult
}