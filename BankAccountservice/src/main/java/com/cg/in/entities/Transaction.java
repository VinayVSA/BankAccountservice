package com.cg.in.entities;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction {

    public Transaction(double transactionAmount, String transactionType, LocalDate transactionDate,int accountNum) {
		super();
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.accountNum=accountNum;
	}

	private int transactionId;
	
	private double transactionAmount;
	
	private String transactionType;
	
	private LocalDate transactionDate;
	
	private int accountNum;
	
}
