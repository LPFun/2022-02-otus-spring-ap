package ru.lpfun.spring.homework02.services

import ru.lpfun.spring.homework02.common.interfaces.ExamService
import ru.lpfun.spring.homework02.common.interfaces.QuestionDao
import ru.lpfun.spring.homework02.common.interfaces.QuestionPrintService

class ExamServiceImpl(
    private val questionDao: QuestionDao,
    private val questionPrintService: QuestionPrintService
) : ExamService {
    override fun exam() {
        val questions = questionDao.getQuestions()
        questionPrintService.print(questions)
    }
}