package com.V.Expenses_traker.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Credit_entity {	
	@Id
	@Column(name="ID")
	private int id;
	@Column(name="AMOUNT")
	private int Amount;
	@Column(name="DATE")
	private Date Date;
	@Column(name="Status")
	private String Category;
	
}
