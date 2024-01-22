package com.andoliver46.microservices.limitsservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andoliver46.microservices.limitsservice.bean.Limits;

@RestController
public class LimitsController {
	
	private Limits limits;
	
	public LimitsController(Limits limits) {
		super();
		this.limits = limits;
	}

	@GetMapping("/limits")
	public Limits retrieveLimits() {
		return limits;
	}
	
	
}
