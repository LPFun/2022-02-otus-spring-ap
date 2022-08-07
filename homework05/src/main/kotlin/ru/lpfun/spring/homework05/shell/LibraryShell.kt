package ru.lpfun.spring.homework05.shell

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption

@ShellComponent
class LibraryShell {

    @ShellMethod(key = ["books", "all"], value = "Список всех книг")
    fun displayAllBooks(): String{
        return "список книг"
    }

    @ShellMethod(key = ["book", "get"], value = "Книга по ид")
    fun displayBookById(id: String): String{
        return "книга с ид $id"
    }

    @ShellMethod(key = ["add"], value = "Добавление книги")
    fun addBook(
        @ShellOption("-t", help = "title") title: String,
        @ShellOption("-a", help = "author") author: String,
        @ShellOption("-g", help = "genre") genre: String
    ): String {
        return "книга была добавлена"
    }

    @ShellMethod(key = ["delete", "del"], value = "Удаление книги")
    fun deleteBookById(id: String): String {
        return "книга была удалена"
    }

    @ShellMethod(key = ["update", "up"], value = "Обновление книги")
    fun updateBook(
        @ShellOption("-t", help = "title") title: String,
        @ShellOption("-a", help = "author") author: String,
        @ShellOption("-g", help = "genre") genre: String
    ): String {
        return "книга была обновлена"
    }
}