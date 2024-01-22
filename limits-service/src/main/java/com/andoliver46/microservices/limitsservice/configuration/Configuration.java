package com.andoliver46.microservices.limitsservice.configuration;

import org.springframework.context.annotation.Bean;

import com.andoliver46.microservices.limitsservice.bean.Limits;

@org.springframework.context.annotation.Configuration
public class Configuration {
	
	@Bean
	public Limits getLimits() {
		return new Limits();
	}

}
