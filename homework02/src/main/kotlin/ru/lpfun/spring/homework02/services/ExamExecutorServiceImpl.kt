package ru.lpfun.spring.homework02.services

import ru.lpfun.spring.homework02.common.interfaces.ExamExecutorService
import ru.lpfun.spring.homework02.common.interfaces.OutputService
import ru.lpfun.spring.homework02.common.interfaces.QuestionDao
import ru.lpfun.spring.homework02.common.interfaces.io.IOService
import ru.lpfun.spring.homework02.common.model.Answer
import ru.lpfun.spring.homework02.common.model.ExamResult
import ru.lpfun.spring.homework02.common.model.Question
import ru.lpfun.spring.homework02.common.model.Student

class ExamExecutorServiceImpl(
    private val ioService: IOService,
    private val questionDao: QuestionDao
) : ExamExecutorService {
    override fun executeExam(student: Student): ExamResult {
        val questions = questionDao.getQuestions()
        var trueAnswersCount = 0
        ioService.println("Enter correct answer id")
        questions.forEach { q ->
            printQuestion(q, ioService)
            val answerId = ioService.getInput()
            val answer = q.answers.find { it.id == answerId } ?: Answer.NONE
            val isCorrect = isCorrectAnswer(answer, q.correctAnswers)
            if (isCorrect) {
                trueAnswersCount += 1
            }
        }
        return ExamResult(questions.size, trueAnswersCount)
    }

    private fun printQuestion(question: Question, outputService: OutputService) {
        outputService.print("${question.id}) ${question.question}")
        outputService.print(System.lineSeparator())
        question.answers.forEachIndexed { i, a ->
            outputService.print(" ${i})${a.answer}")
        }
        outputService.print(System.lineSeparator())
    }

    private fun isCorrectAnswer(answer: Answer, correctAnswers: List<Answer>): Boolean {
        return correctAnswers.any { it.answer == answer.answer }
    }
}