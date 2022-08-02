package ru.lpfun.spring.homework03.providers

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.lpfun.spring.homework03.common.interfaces.ExamFilePathProvider
import ru.lpfun.spring.homework03.config.ExamProps

@TestPropertySource(properties = ["app.settings.lang=en"])
@SpringBootTest(classes = [ExamFilePathProviderImpl::class])
@ExtendWith(SpringExtension::class)
internal class ExamFilePathProviderImplTest {

    @MockkBean
    private lateinit var examProps: ExamProps

    @Autowired
    private lateinit var examFilePathProvider: ExamFilePathProvider

    @Test
    fun `generate file path`() {
        every { examProps.filePath } returns "data/questions.csv"
        val filePath = examFilePathProvider.path()
        assertEquals("data/questions_en.csv", filePath)
    }
}