package com.example.SimpleBankApp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence. FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence. GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
	

      @Id
      @GeneratedValue(strategy =GenerationType.IDENTITY)
      private Long id;
      private String type;
      private double amount;
      private LocalDateTime date;
      @ManyToOne (fetch= FetchType.LAZY)
      @JoinColumn(name = "account_id")
      private Account account;
      
      public Transaction() {}

	public Transaction(String type, double amount, LocalDateTime date, Account account) {
		super();
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
