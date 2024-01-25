package com.andoliver46.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.andoliver46.microservices.currencyconversionservice.controller.domain.CurrencyConversion;

@RestController
public class CurrencyConversionController {
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		ResponseEntity<CurrencyConversion> response = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, from, to);
		CurrencyConversion currencyConversion = response.getBody();
		currencyConversion.setQuantity(quantity);
		currencyConversion.calculateTotalAmount();
		
		return currencyConversion;
	}

}
