package com.andoliver46.microservices.currencyexchangeservice.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.andoliver46.microservices.currencyexchangeservice.domain.CurrencyExchange;
import com.andoliver46.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class CurrencyExchangeController {
	
	private final Environment environment;
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository currencyExchangeRepository) {
		this.environment = environment;
		this.currencyExchangeRepository = currencyExchangeRepository;
	}

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to) {
		CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to).orElseThrow(() -> new EntityNotFoundException("Unable to find data for " + from + " to " + to));
		currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
		return currencyExchange;
	}
	
}
