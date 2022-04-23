package ru.lpfun.spring.homework02.services

import ru.lpfun.spring.homework02.common.interfaces.QuestionPrintService
import ru.lpfun.spring.homework02.common.model.Questions

class QuestionPrintServiceImpl : QuestionPrintService {
    override fun print(questions: Questions) {
        questions.questionList.forEach { q ->
            println("${q.id}) ${q.question}")
            q.answers.forEachIndexed { i, a ->
                print(" ${i + 1}.${a.answer}")
            }
            println()
        }
    }
}

