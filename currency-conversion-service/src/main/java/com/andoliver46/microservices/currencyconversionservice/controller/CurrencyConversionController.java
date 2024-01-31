package com.andoliver46.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.andoliver46.microservices.currencyconversionservice.controller.proxy.CurrencyExchangeProxy;
import com.andoliver46.microservices.currencyconversionservice.domain.CurrencyConversion;

@RestController
public class CurrencyConversionController {
	
	private final RestTemplate restTemplate;
	private final CurrencyExchangeProxy proxy;
	
	public CurrencyConversionController(CurrencyExchangeProxy proxy, RestTemplate restTemplate) {
		super();
		this.proxy = proxy;
		this.restTemplate = restTemplate;
	}

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		//ResponseEntity<CurrencyConversion> response = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, from, to);
		ResponseEntity<CurrencyConversion> response = restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, from, to);
		CurrencyConversion currencyConversion = response.getBody();
		currencyConversion.setQuantity(quantity);
		currencyConversion.calculateTotalAmount();
		currencyConversion.setEnvironment(currencyConversion.getEnvironment() + " rest template");
		
		return currencyConversion;
	}
	
	@GetMapping("/v2/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyV2(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		CurrencyConversion currencyConversion = proxy.getExchangeValue(from, to);
		currencyConversion.setQuantity(quantity);
		currencyConversion.calculateTotalAmount();
		currencyConversion.setEnvironment(currencyConversion.getEnvironment() + " feign");
		
		return currencyConversion;
	}

}
