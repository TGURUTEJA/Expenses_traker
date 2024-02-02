package com.Expenses_traker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Expenses_traker.entity.Expenses_entity;
import com.Expenses_traker.repository.Expenses_repository;

@Service
public class Expenses_service {
	@Autowired
	Expenses_repository ExpensesRepository;
	public ResponseEntity<Object> add(Expenses_entity e){
		ExpensesRepository.save(e);
		return new ResponseEntity<Object>("Created",HttpStatus.CREATED);
	}

}
