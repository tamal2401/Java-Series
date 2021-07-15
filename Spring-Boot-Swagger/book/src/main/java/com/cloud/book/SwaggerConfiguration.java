package com.cloud.book;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

    @Value("${security.swagger.enabled:false}")
    public boolean swaggerFlag;

    @Value("${security.swagger.authType:basicAuth}")
    public String securityType;

    private SecurityScheme basicAuthScheme() {
        return new BasicAuth("basicAuth");
    }

    private ApiKey apiKey() {
        return new ApiKey("oauth", "Authorization", "header");
    }

    private SecurityReference[] basicAuthReference() {
        if (swaggerFlag) {
            if(securityType.equalsIgnoreCase("basicAuth")){
                SecurityReference basicAuthReference = new SecurityReference("basicAuth", new AuthorizationScope[0]);
                return new SecurityReference[]{basicAuthReference};
            }else if(securityType.equalsIgnoreCase("oauth")){
                SecurityReference oauthReference = new SecurityReference("oauth", new AuthorizationScope[0]);
                return new SecurityReference[]{oauthReference};
            }
        }
        return new SecurityReference[0];
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(basicAuthReference()))
                .forPaths(PathSelectors.ant("/**"))
                .build();
    }

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cloud.book.controller"))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiEndpointsInfo());
        if (swaggerFlag) {
            if(securityType.equalsIgnoreCase("basicAuth")){
                docket.securityContexts(Arrays.asList(securityContext()));
                docket.securitySchemes(Arrays.asList(basicAuthScheme()));
            }else if(securityType.equalsIgnoreCase("oauth")){
                docket.securityContexts(Arrays.asList(securityContext()));
                docket.securitySchemes(Arrays.asList(apiKey()));
            }
        }
        return docket;
    }

    private ApiInfo apiEndpointsInfo() {
        return new ApiInfoBuilder()
                .title("Book Service API")
                .description("A sample service to demonstrate swagger 2 implementation adn required beans")
                .version("v0.1-SNAPSHOT")
                .license("MIT")
                .licenseUrl("need to look for MIT license url")
                .build();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/documentation/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true);
        registry.addRedirectViewController("/documentation/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/documentation/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/documentation/swagger-resources", "/swagger-resources");
        registry.addRedirectViewController("/documentation", "/documentation/swagger-ui.html");
        registry.addRedirectViewController("/documentation/", "/documentation/swagger-ui.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/documentation/**").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId("test-app-client-id")
                .clientSecret("test-app-client-secret")
                .realm("test-app-realm")
                .appName("test-app")
                .scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .build();
    }
}
