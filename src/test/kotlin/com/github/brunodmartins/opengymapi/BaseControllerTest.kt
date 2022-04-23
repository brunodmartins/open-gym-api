package com.github.brunodmartins.opengymapi

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@WebAppConfiguration
abstract class BaseControllerTest {
    protected lateinit var mvc: MockMvc

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @BeforeEach
    protected fun setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    fun <T: Any>  toJson(value: T): String {
        return objectMapper.writeValueAsString(value)
    }
}