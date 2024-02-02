package com.Expenses_traker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Expenses_traker.entity.Income_catogary_entity;
import com.Expenses_traker.repository.Income_catogary_repository;

@Service
public class Income_catogary_service {
	@Autowired
	Income_catogary_repository incomeCatogaryRepository;
	public ResponseEntity<Object> add(Income_catogary_entity Income_catogary){
		incomeCatogaryRepository.save(Income_catogary);
		return new ResponseEntity<Object>("Created",HttpStatus.CREATED);
	}

}
