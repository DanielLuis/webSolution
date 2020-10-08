package br.com.springboot.app;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import({SpringDataRestConfiguration.class})
public class SpringFoxConfig {                                    
	@Bean
	public Docket apiDocket() {
	    return new Docket(DocumentationType.SWAGGER_2)
	            .select()
	            .apis(RequestHandlerSelectors.basePackage("br.com.springboot.app.controller"))
	            .paths(PathSelectors.any())
	            .build()
	            .apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
	    return new ApiInfo(
	            "WEB SOLUTION",
	            "web application template",
	            "1.0",
	            "TERMS OF SERVICE URL",
	            new Contact("DANIEL","URL","EMAIL"),
	            "LICENSE",
	            "LICENSE URL",
	            Collections.emptyList()
	    );
	}
}