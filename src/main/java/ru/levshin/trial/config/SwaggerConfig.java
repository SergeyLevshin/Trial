package ru.levshin.trial.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis (RequestHandlerSelectors.basePackage("ru.levshin.trial.restcontroller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title ("Bars Hunting RESTful API")
                .contact(new Contact("ЦИТ БАРС", "http://barsim.ru/content/kontakty", "arenda@bars-arenda.ru"))
                .description ("Барс Охота описание API")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
