package com.Expenses_traker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Expenses_traker.entity.Income_catogary_entity;
import com.Expenses_traker.service.Income_catogary_service;
@RestController
public class Income_catogary_controller {
	@Autowired
	Income_catogary_service incomeCatogaryService;
	
	@PostMapping("/api/addIncomeCatogary")
	public ResponseEntity<Object> addExpencesCatogary(@RequestBody Income_catogary_entity incomeCatogary){
		return incomeCatogaryService.add(incomeCatogary);
	}
}
