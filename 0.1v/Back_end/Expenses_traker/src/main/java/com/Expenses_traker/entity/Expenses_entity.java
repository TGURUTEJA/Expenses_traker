package com.Expenses_traker.entity;


import jakarta.persistence.*;

@Entity
public class Expenses_entity {

	@Id
	@Column(name="ID")
	private int Id;
	@Column(name="DATE")
	private String Date;
	@Column(name="AMOUNT")
	private int Amount;
	@Column(name="CATEGORY")
	private String Category;
	@Column(name="TYPE")
	private String Type;
	public Expenses_entity(int id, String date, int amount, String category, String type) {
		super();
		Id = id;
		Date = date;
		Amount = amount;
		Category = category;
		Type = type;
	}
	public Expenses_entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	@Override
	public String toString() {
		return "Expenses_entity [Id=" + Id + ", Date=" + Date + ", Amount=" + Amount + ", Category=" + Category
				+ ", Type=" + Type + "]";
	}
	
	
	
}
