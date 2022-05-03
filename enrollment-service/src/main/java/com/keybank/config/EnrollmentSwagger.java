/**@Copyright 2022 keybank pvt ltd.
 * All rights are reserved,you should 
*disclose the information outside
* otherwise terms and condition will apply
 */
package com.keybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author YNBR 14-Mar-2022
 *
 */
@Configuration
public class EnrollmentSwagger {

	  @Bean
	    public Docket createRestApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	        		.apiInfo(new ApiInfoBuilder()
	        		.description("Enrollment service API")
	        		.title("Enrollment Service Api")
	        		.version("1.0.0")
	        		.build())
	        		.enable(true)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.keybank.controller"))
	                .paths(PathSelectors.any())
	                .build();
	    }


}
