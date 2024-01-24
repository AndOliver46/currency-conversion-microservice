package com.andoliver46.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.andoliver46.microservices.currencyexchangeservice.domain.CurrencyExchange;

@RestController
public class CurrencyExchangeController {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to) {
		
		return new CurrencyExchange(1001L, from, to, BigDecimal.valueOf(65));
		
		
	}
	
}
