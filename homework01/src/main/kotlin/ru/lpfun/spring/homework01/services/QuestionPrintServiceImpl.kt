package ru.lpfun.spring.homework01.services

import ru.lpfun.spring.homework01.common.interfaces.QuestionPrintService
import ru.lpfun.spring.homework01.common.model.Questions

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

