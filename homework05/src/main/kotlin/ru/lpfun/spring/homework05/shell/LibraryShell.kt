package ru.lpfun.spring.homework05.shell

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption
import ru.lpfun.spring.homework05.common.models.AuthorModel
import ru.lpfun.spring.homework05.common.models.BookModel
import ru.lpfun.spring.homework05.common.models.GenreModel
import ru.lpfun.spring.homework05.common.services.IBookService

@ShellComponent
class LibraryShell(
    private val bookService: IBookService
) {
    @ShellMethod(key = ["books", "all"], value = "Список всех книг")
    fun displayAllBooks(): String {
        val books = bookService.getAllBooks()
        return "Список книг\n${books.toView()}"
    }

    @ShellMethod(key = ["book", "get"], value = "Книга по ид")
    fun displayBookById(
        @ShellOption("-i", help = "id") id: Long
    ): String {
        val book = bookService.getBookById(id)
        return book.toView()
    }

    @ShellMethod(key = ["add"], value = "Добавление книги")
    fun addBook(
        @ShellOption("-i", help = "id") id: Long,
        @ShellOption("-t", help = "title") title: String,
        @ShellOption("-a", help = "author") author: String,
        @ShellOption("-g", help = "genre") genre: String
    ): String {
        val book = bookService.addBook(
            BookModel(
                id = id,
                title = title,
                author = AuthorModel(
                    authorName = author
                ),
                genre = GenreModel(
                    genreName = genre
                )
            )
        )
        return "Добавлено\n${book.toView()}"
    }

    @ShellMethod(key = ["delete", "del"], value = "Удаление книги")
    fun deleteBookById(
        @ShellOption("-i", help = "id") id: Long
    ): String {
        bookService.deleteBookById(id)
        return "Удалено"
    }

    @ShellMethod(key = ["update", "up"], value = "Обновление книги")
    fun updateBook(
        @ShellOption("-i", help = "id") id: Long,
        @ShellOption("-t", help = "title") title: String,
        @ShellOption("-a", help = "author") author: String,
        @ShellOption("-g", help = "genre") genre: String
    ): String {
        val book = bookService.updateBook(
            BookModel(
                id = id,
                title = title,
                author = AuthorModel(
                    authorName = author
                ),
                genre = GenreModel(
                    genreName = genre
                )
            )
        )
        return "Обновлено\n${book.toView()}"
    }
}