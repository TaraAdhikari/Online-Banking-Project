package com.userFront.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SavingsTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String description;
	private Date date;
	private String type;
	private String status;
	private double amount;// we are not doing any arithmetic calculations here
							// for this number so we are not using bigdecimal
							// for this amount otherwise we have to use.

	private BigDecimal availableBalance;// but here we gonna add or reduce
										// numbers
	@ManyToOne
	@JoinColumn(name= "savings_account_id")
	private SavingsAccount savingsAccount;
	
	
	public SavingsTransaction() {
		// TODO Auto-generated constructor stub
	}


	public SavingsTransaction(Date date, String description, String type, String status, double amount,
			BigDecimal availableBalance, SavingsAccount savingsAccount) {
		this.description = description;
		this.date = date;
		this.type = type;
		this.status = status;
		this.amount = amount;
		this.availableBalance = availableBalance;
		this.savingsAccount = savingsAccount;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}


	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}


	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}


	public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}
	
	
}
