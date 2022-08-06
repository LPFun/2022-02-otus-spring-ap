package ru.lpfun.spring.homework04.parser

import com.opencsv.bean.CsvToBeanBuilder
import java.io.Reader

inline fun <reified T> parseCsv(reader: Reader): List<T> {
    return CsvToBeanBuilder<T>(reader)
        .withSeparator(';')
        .withType(T::class.java)
        .build()
        .parse()
}
