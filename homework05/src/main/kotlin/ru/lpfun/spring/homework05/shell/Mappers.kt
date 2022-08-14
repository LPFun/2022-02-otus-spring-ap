package ru.lpfun.spring.homework05.shell

import ru.lpfun.spring.homework05.common.models.BookModel

fun BookModel.toView(): String {
    return "Книга\n\tНомер: $id\n\tНазвание: $title\n\tАвтор: ${author.authorName}\n\tЖанр: ${genre.genreName}"
}

fun List<BookModel>.toView(): String {
    return joinToString("\n") { it.toView() }
}