package ru.lpfun.spring.homework03.common.interfaces

import ru.lpfun.spring.homework03.common.model.ExamResult

interface ExamExecutorService {
    fun executeExam(): ExamResult
}