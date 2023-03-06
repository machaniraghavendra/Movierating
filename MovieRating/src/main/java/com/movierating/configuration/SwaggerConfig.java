package com.movierating.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {
	@Bean
	public Docket api() { 
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.select()
				.paths(PathSelectors.ant("/movie/*"))
				.apis(RequestHandlerSelectors.basePackage("com.movierating"))              
				.build();                                           
	}
	@Bean
	public InternalResourceViewResolver defaultViewResolver() {
		return new InternalResourceViewResolver();
	}	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Movie rating Application")
				.version("2.0.0")
				.description("Gives latest movie ratings")
				.build();
	}
}
