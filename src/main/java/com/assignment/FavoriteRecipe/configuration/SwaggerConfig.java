package com.assignment.FavoriteRecipe.configuration;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import org.springframework.context.annotation.Bean;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/api/posts.*"), regex("/api/v1/recipes/*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Serving API")
                .description("Serving API reference for developers and Business user")
                .termsOfServiceUrl("http://serving.com")
                .contact("malhotrashefali19@gmail.com").license("Serving-API License")
                .licenseUrl("servingapi@gmail.com").version("1.0").build();
    }
}
