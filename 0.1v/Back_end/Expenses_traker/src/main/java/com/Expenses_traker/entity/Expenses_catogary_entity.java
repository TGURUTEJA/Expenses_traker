package com.Expenses_traker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Expenses_catogary_entity {
	
	@Id
	@Column(name="ID")
	private int id;
	@Column(name="CATEGORY")
	private String category;
	@Column(name="BUDGET")
	private int budget;
	public Expenses_catogary_entity(int id, String category, int budget) {
		super();
		this.id = id;
		this.category = category;
		this.budget = budget;
	}
	public Expenses_catogary_entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	@Override
	public String toString() {
		return "Expenses_catogary_entity [id=" + id + ", category=" + category + ", budget=" + budget + "]";
	}
	

}
