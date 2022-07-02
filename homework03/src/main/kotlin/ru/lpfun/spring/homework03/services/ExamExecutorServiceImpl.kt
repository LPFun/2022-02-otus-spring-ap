package ru.lpfun.spring.homework03.services

import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import ru.lpfun.spring.homework03.common.interfaces.ExamExecutorService
import ru.lpfun.spring.homework03.common.interfaces.OutputService
import ru.lpfun.spring.homework03.common.interfaces.QuestionDao
import ru.lpfun.spring.homework03.common.interfaces.io.IOService
import ru.lpfun.spring.homework03.common.model.Answer
import ru.lpfun.spring.homework03.common.model.ExamResult
import ru.lpfun.spring.homework03.common.model.Question
import ru.lpfun.spring.homework03.config.ExamProps
import java.util.*

@Service
class ExamExecutorServiceImpl(
    private val ioService: IOService,
    private val questionDao: QuestionDao,
    private val examProps: ExamProps,
    private val messageSource: MessageSource
) : ExamExecutorService {

    override fun executeExam(): ExamResult {
        val questions = questionDao.getQuestions()
        var trueAnswersCount = 0
        ioService.println(messageSource.getMessage("exam.instruction", null, Locale.getDefault()))
        questions.forEach { q ->
            printQuestion(q, ioService)
            val answerId = ioService.getInput()
            val answer = try {
                q.answers[answerId.toInt() - 1]
            } catch (e: Exception) {
                Answer.NONE
            }
            val isCorrect = isCorrectAnswer(answer, q.correctAnswers)
            if (isCorrect) {
                trueAnswersCount += 1
            }
        }
        return ExamResult(questions.size, trueAnswersCount, trueAnswersCount >= examProps.numOfCorrectAnswersToPassExam)
    }

    private fun printQuestion(question: Question, outputService: OutputService) {
        outputService.print("${question.id}) ${question.question}")
        outputService.print(System.lineSeparator())
        question.answers.forEachIndexed { i, a ->
            outputService.print(" ${i + 1})${a.answer}")
        }
        outputService.print(System.lineSeparator())
    }

    private fun isCorrectAnswer(answer: Answer, correctAnswers: List<Answer>): Boolean {
        return correctAnswers.any { it.answer == answer.answer }
    }
}