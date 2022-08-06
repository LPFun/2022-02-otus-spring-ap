package ru.lpfun.spring.homework04.providers

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.lpfun.spring.homework04.common.interfaces.ExamFilePathProvider
import ru.lpfun.spring.homework04.config.AppSettings
import ru.lpfun.spring.homework04.config.ExamProps

@TestPropertySource(properties = ["app.settings.lang=en"])
@SpringBootTest(classes = [ExamFilePathProviderImpl::class])
@ExtendWith(SpringExtension::class)
internal class ExamFilePathProviderImplTest {

    @MockkBean
    private lateinit var examProps: ExamProps

    @MockkBean
    private lateinit var appSettings: AppSettings

    @Autowired
    private lateinit var examFilePathProvider: ExamFilePathProvider

    @Test
    fun `generate file path`() {
        every { examProps.filePath } returns "data/questions.csv"
        every { appSettings.lang } returns "en"
        val filePath = examFilePathProvider.path()
        assertEquals("data/questions_en.csv", filePath)
    }
}