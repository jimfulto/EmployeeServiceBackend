package com.galvanize.employeedb.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//This class has more than one Bean.
@Configuration
//enables Swagger support in the class
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    /**
     * http://localhost:8080/swagger-ui.html#
     * <p>
     * The select() method called on the Docket bean instance returns an ApiSelectorBuilder,
     * which provides the apis() and paths() methods to filter the controllers and methods being documented using String predicates.
     * In the code, the RequestHandlerSelectors.basePackage predicate matches the com.galvanize.employeedb.Controllers base package to filter the API.
     * The regex parameter passed to paths() acts as an additional filter to generate documentation only for the path starting with /product.
     *
     *
     *
     * Docket, Springfox’s, primary api configuration mechanism is initialized for swagger specification 2.0
     *
     * select() returns an instance of ApiSelectorBuilder to give fine grained control over the endpoints exposed via swagger.
     *
     * apis() allows selection of RequestHandler's using a predicate. The example here uses an any predicate (default). Out of the box predicates provided are any, none, withClassAnnotation, withMethodAnnotation and basePackage.
     *
     * paths() allows selection of Path's using a predicate. The example here uses an any predicate (default). Out of the box we provide predicates for regex, ant, any, none.
     *
     * The selector needs to be built after configuring the api and path selectors.
     *
     * com.galvanize.employeedb.controller
     *
     * @return
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.galvanize.employeedb.controller"))
                //  .paths(regex("/employee.*")) // This will call only specific controller in Swagger Doc.
                .paths(PathSelectors.any()) //This will pick all controllers in the above mentioned package.
                .build()
                .apiInfo(metaData());

    }

    /**
     * metaData() method that returns and ApiInfo object initialized with information about our API.
     *
     * @return
     */
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Employee records documentation")
                .description("\"Employee records REST APIs\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Swagger 2 documentation", "https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/", "john@springfrmework.guru"))
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}