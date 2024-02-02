package com.Expenses_traker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Income_catogary_entity {

	@Id
	@Column(name="ID")
	private int id;
	@Column(name="CATEGORY")
	private String category;
	@Column(name="EXPECTED_INCOME")
	private int expectedIncome;
	public Income_catogary_entity(int id, String category, int expectedIncome) {
		super();
		this.id = id;
		this.category = category;
		this.expectedIncome = expectedIncome;
	}
	public Income_catogary_entity() {
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
	public int getExpectedIncome() {
		return expectedIncome;
	}
	public void setExpectedIncome(int expectedIncome) {
		this.expectedIncome = expectedIncome;
	}
	@Override
	public String toString() {
		return "Income_catogary_entity [id=" + id + ", category=" + category + ", expectedIncome=" + expectedIncome
				+ "]";
	}
	

}