package com.Expenses_traker.entity;

import java.sql.Date;

import jakarta.persistence.*;


@Entity
public class Credit_entity {	
	@Id
	@Column(name="ID")
	private int id;
	@Column(name="AMOUNT")
	private int amount;
	@Column(name="DATE")
	private Date date;
	@Column(name="Status")
	private String status;
	public Credit_entity(int id, int amount, Date date, String status) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.status = status;
	}
	public Credit_entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getstatus() {
		return status;
	}
	public void setstatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Credit_entity [id=" + id + ", amount=" + amount + ", date=" + date + ", status=" + status + "]";
	}
	
	
}
