package com.github.brunodmartins.opengymapi.platform.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.oas.annotations.EnableOpenApi
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2 //Enable swagger 2.0 spec
@EnableOpenApi //Enable open api 3.0.3 spe
class SwaggerConfiguration {

    @Bean
    fun openGymApi(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .groupName("business-api")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.github.brunodmartins.opengymapi.core"))
            .build()
            .apiInfo(apiInfo())
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Open-Gym API")
            .description("OpenGym helps a Gym load custom exercises into recipes for customer.")
            .contact(Contact("Bruno", "github.com/brunodmartins", ""))
            .license("GPLv2.0")
            .build()
    }
}