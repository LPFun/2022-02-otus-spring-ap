package ru.lpfun.spring.homework02.common.interfaces

import ru.lpfun.spring.homework02.common.model.ExamResult

interface ExamExecutorService {
    fun executeExam(): ExamResult
}