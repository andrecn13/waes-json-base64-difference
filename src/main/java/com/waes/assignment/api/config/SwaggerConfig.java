package com.waes.assignment.api.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * Class to configure api documentation generation strategy
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Configure swagger to generate api documentation
     * @return
     */
    public Docket buildApiDocumentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(new Tag("diff", "Endpoint to manage diff resources"))
                .apiInfo(buildApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.waes.assignment.api.contract"))
                .build();
    }

    /**
     * Configure metadata of api
     * @return an object representing metadata of api
     */
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("Waes json base64 difference API")
                .description("Waes Assignment API")
                .version("1.0.0")
                .build();
    }
}
