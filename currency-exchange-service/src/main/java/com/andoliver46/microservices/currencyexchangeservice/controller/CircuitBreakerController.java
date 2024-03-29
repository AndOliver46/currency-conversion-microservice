package com.andoliver46.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/sample-api")
	//@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
	//@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
	//@RateLimiter(name = "default") //Limits the number of calls in a certain time limit configured in properties file
	@Bulkhead(name = "default")
	public String sampleApi() {
		logger.info("Sample API call received");
		//ResponseEntity<String> returnedEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
		//return returnedEntity.getBody();
		return "Sample API";
	}
	
	public String hardcodedResponse(Exception ex) {
		return "FallBack Response: " + ex.getLocalizedMessage();
	}
	
}
