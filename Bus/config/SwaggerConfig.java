package com.redbus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 // Note: For Swagger 3.0.0, you can remove this annotation
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30) // Use OAS_30 for OpenAPI 3.0
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.redbus"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Starlight Technologies Pvt Ltd API Documentation")
                .description("API documentation for the Starlight Technologies Pvt Ltd services")
                .version("1.0.0")
                .termsOfServiceUrl("Starlight software pvt ltd terms and condition")
                .contact(new Contact("Adfar", "stlsoft.com", "adfaramushtaq@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

}
