package com.security;



import com.google.common.base.Predicates;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@OpenAPIDefinition(security = {@SecurityRequirement(name = "bearer-key")})
public class SwaggerConfig {
    @Bean
    public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
        return openApi -> openApi.getComponents()
                .addSecuritySchemes("bearer-key",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"));
    }
//    @Bean
//    public Docket productApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .paths(PathSelectors.ant("/api/**"))
//                .build();
//    }
}