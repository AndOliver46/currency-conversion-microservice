package com.andoliver46.microservices.currencyexchangeservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andoliver46.microservices.currencyexchangeservice.domain.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
	Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}
