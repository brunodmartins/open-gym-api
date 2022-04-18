package com.github.brunodmartins.opengymapi.platform.swagger

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.util.StringUtils
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SwaggerUIWebMvc : WebMvcConfigurer {
    private var baseURL : String = "localhost"

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        val baseUrl: String = StringUtils.trimTrailingCharacter(this.baseURL, '/')
        registry.addResourceHandler("$baseUrl/swagger-ui/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
            .resourceChain(false)
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("$baseURL/swagger-ui/")
            .setViewName("forward:$baseURL/swagger-ui/index.html")
    }
}