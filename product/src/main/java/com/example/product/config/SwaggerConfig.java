package com.example.product.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket customDocket() {
		String groupName = "Swagger";
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(" com.example.product"))
				.paths(Predicates.not(PathSelectors.regex("/error.*"))).build()
				.globalOperationParameters(commonParameters()).groupName(groupName).apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Product Service")
				.description("Provides endpoint for Product Management APIs").license("Terms of service")
				.license("License of API").version("Pegasus Platform").build();
	}

	private List<Parameter> commonParameters() {
		List<Parameter> parameters = new ArrayList<>();
		parameters.add(new ParameterBuilder().name("x-user-id").description("User Identification number")
				.modelRef(new ModelRef("string")).parameterType("header").required(false).build());
		return parameters;
	}

}