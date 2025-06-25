package com.example.SimpleBankApp.service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.SimpleBankApp.entity.Account;
import com.example.SimpleBankApp.entity.Transaction;
import com.example.SimpleBankApp.repo.AccountRepo;
import com.example.SimpleBankApp.repo.TransactionRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BankService {

	@Autowired
    private AccountRepo accountRepo;
	
	@Autowired
	private TransactionRepo transactionRepo;
	
	// Method to create a new account
	public Account createAccount (String holderName, double initialBalance) {
	Account newAccount = new Account(holderName,initialBalance);
	return accountRepo.save(newAccount);
	
	}
	
// Method to retrieve all accounts
	public List<Account> getAllAccounts () {
		return accountRepo.findAll();
		}
	
// Method to deposit to an account
	public void deposit (Long accountId, double amount) {
		Account account = accountRepo.findById(accountId)
		       .orElseThrow (()->new IllegalArgumentException("Invalid account ID"));
		account.setBalance (account.getBalance() + amount);
		accountRepo.save(account);
		
		Transaction transaction = new Transaction("Deposit", amount, LocalDateTime.now(), account);
		transactionRepo.save(transaction);
	}
	
// Method to withdraw from an account
	public void withdraw (Long accountId, double amount) {
		Account account =accountRepo.findById(accountId)
		.orElseThrow (()->new IllegalArgumentException("Invalid account ID"));
		if(account.getBalance() < amount) {
		throw new IllegalArgumentException("Insufficient balance");
		}
		account.setBalance (account.getBalance() - amount);
		accountRepo.save(account);
		Transaction transaction = new Transaction ("Withdraw", amount, LocalDateTime.now(), account);
		transactionRepo.save(transaction);
		}
	
// Method to get transactions history
	public List<Transaction> getTransactionHistory(Long accountId, String type, LocalDateTime startDate, LocalDateTime endDate) {
	    return transactionRepo.findByAccountIdAndOptionalFilters(accountId, type, startDate, endDate);
	}

	
	
	
	
	
}
