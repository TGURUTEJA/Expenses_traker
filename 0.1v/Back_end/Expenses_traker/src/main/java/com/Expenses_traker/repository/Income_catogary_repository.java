package com.Expenses_traker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Expenses_traker.entity.Income_catogary_entity;


@Repository
public interface Income_catogary_repository extends JpaRepository<Income_catogary_entity, Integer> {

}
