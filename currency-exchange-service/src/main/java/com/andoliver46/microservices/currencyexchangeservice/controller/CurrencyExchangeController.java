package com.andoliver46.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.andoliver46.microservices.currencyexchangeservice.domain.CurrencyExchange;

@RestController
public class CurrencyExchangeController {
	
	private final Environment environment;
	
	public CurrencyExchangeController(Environment environment) {
		this.environment = environment;
	}



	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to) {
		CurrencyExchange currencyExchange = new CurrencyExchange(1001L, from, to, BigDecimal.valueOf(65));
		currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
		return currencyExchange;
	}
	
}
