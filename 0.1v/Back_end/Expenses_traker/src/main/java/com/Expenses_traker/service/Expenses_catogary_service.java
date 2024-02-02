package com.Expenses_traker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Expenses_traker.entity.Expenses_catogary_entity;
import com.Expenses_traker.repository.Expenses_catogary_repository;

@Service
public class Expenses_catogary_service {

	@Autowired
	Expenses_catogary_repository expensesCatogaryRepository;
	public ResponseEntity<Object> add(Expenses_catogary_entity Expenses_catogary){
		expensesCatogaryRepository.save(Expenses_catogary);
		return new ResponseEntity<Object>("Created",HttpStatus.CREATED);
	}
}
