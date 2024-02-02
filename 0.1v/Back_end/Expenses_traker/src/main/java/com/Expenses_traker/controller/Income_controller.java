package com.Expenses_traker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Expenses_traker.entity.Income_entity;
import com.Expenses_traker.service.Income_service;
@RestController
public class Income_controller {
	@Autowired
	Income_service incomeService;
	
	@PostMapping("/api/addIncome")
	public ResponseEntity<Object> addIncome(@RequestBody Income_entity income){
		return incomeService.add(income);
	}
}
