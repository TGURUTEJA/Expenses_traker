package com.Expenses_traker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Expenses_traker.entity.Expenses_catogary_entity;

@Repository
public interface Expenses_catogary_repository extends JpaRepository<Expenses_catogary_entity, Integer> {

}
