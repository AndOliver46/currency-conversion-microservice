package com.andoliver46.microservices.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		
		return builder.routes()
			.route(p -> p
					.path("/get")
					.filters(f -> f.addRequestHeader("MyHeader", "MyURI"))
					.uri("http://httpbin.org:80"))
			.route(p -> p
					.path("/currency-exchange/**")
					.uri("lb://currency-exchange"))
			.route(p -> p
					.path("/v2/currency-conversion/**")
					.uri("lb://currency-conversion"))
			.route(p -> p
					.path("/currency-conversion/**")
					.filters(f -> f.rewritePath("/currency-conversion/(?<segment>.*)", "/v2/currency-conversion/${segment}"))
					.uri("lb://currency-conversion"))
			.build();
	}

}
