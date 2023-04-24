package com.madison.motor;

import javax.persistence.EntityManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.querydsl.jpa.impl.JPAQueryFactory;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;


@EnableWebMvc
@SpringBootApplication
@EnableTransactionManagement
public class MadisonAdminApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MadisonAdminApiApplication.class, args);
	}
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Card Vault Service").apiInfo(apiInfo()).select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud")))
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.data.rest.webmvc")))
				.build().securitySchemes(Lists.newArrayList(apiKey()));

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Payment Restful Service's ").description(
				"Payment API's service Developed in spring boot. This is the centralized micro service. Objective of this application is to consume all services "
						+ "The APIs are web service methods based on REST protocol."
						+ " The APIs will be called using secure HTTPS protocol.\n")
				.license("Apache License Version 2.0").version("1.1.0").build();
	}

	@Bean
	public SecurityConfiguration securityInfo() {
		return new SecurityConfiguration(null, null, null, null, "", ApiKeyVehicle.HEADER, "Authorization", "");
	}

	private ApiKey apiKey() {
		return new ApiKey("Authorization", "Authorization", "header");
	}
	
	
	
	@Bean
	public JPAQueryFactory getJPAQueryFactory(EntityManager entityManager) {
		return new JPAQueryFactory(entityManager);
	}
}
