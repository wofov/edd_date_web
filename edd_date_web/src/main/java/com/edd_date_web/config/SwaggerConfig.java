package com.edd.date.config;

import com.edd.date.constants.WebConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    //http://localhost:8081/swagger-ui.html#/

    //yml파일에 꼭 추가 하기!!!!!
    //mvc:
    //pathmatch:
    //matching-strategy: ant_path_matcher

    @Bean
    public Docket eddApi(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(WebConstants.API_NAME)
                        .version(WebConstants.API_VERSION)
                        .description(WebConstants.API_DESCRIPTION)
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage(WebConstants.BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();

    }


}
