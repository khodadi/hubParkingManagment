package com.security;



import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//@OpenAPIDefinition(security = {@SecurityRequirement(name = "bearer-key")})
//@ConfigurationProperties("app.api")
//@ConditionalOnProperty(name="app.api.swagger.enable", havingValue = "true", matchIfMissing = false)
public class SwaggerConfig {
    @Bean
    public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
        return openApi -> openApi.getComponents()
                .addSecuritySchemes("bearer-key",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"));
    }
    private String version;
    private String title;
    private String description;
    private String basePackage;
    private String contactName;
    private String contactEmail;
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com"))
//                .paths(PathSelectors.any())
//                .build()
////                .directModelSubstitute(LocalDate.class, Date.class)
////                .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
//                .apiInfo(apiInfo());
//    }

//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("title")
//                .description("description")
//                .version("version")
//                .contact(new Contact("contactName", null, "contactEmail"))
//                .build();
//    }
}