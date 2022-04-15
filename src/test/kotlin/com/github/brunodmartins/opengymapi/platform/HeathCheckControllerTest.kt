package com.github.brunodmartins.opengymapi.platform

import com.github.brunodmartins.opengymapi.BaseControllerTest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
class HeathCheckControllerTest : BaseControllerTest() {

    @Test
    fun ping() {
        val uri = "/ping"
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk)
            .andExpect(content().string("pong"))
    }
}