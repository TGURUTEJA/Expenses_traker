package com.Expenses_traker.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Income_entity {
	@Id
	@Column(name="ID")
	private int id;
	@Column(name="DATE")
	private Date date;
	@Column(name="AMOUNT")
	private int amount;
	@Column(name="CATEGORY")
	private String category;
	@Column(name="TYPE")
	private String type;
	public Income_entity(int id, Date date, int amount, String category, String type) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.category = category;
		this.type = type;
	}
	public Income_entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Income_entity [id=" + id + ", date=" + date + ", amount=" + amount + ", category=" + category
				+ ", type=" + type + "]";
	}
	
	
}