package com.Expenses_traker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Expenses_traker.entity.Credit_entity;
import com.Expenses_traker.repository.Credit_repository;

@Service
public class Credit_service {

	@Autowired
	Credit_repository creditRepository;
	public ResponseEntity<Object> add(Credit_entity Credit){
		creditRepository.save(Credit);
		return new ResponseEntity<Object>("Created",HttpStatus.CREATED);
	}
}
