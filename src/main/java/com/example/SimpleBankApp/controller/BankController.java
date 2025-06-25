package com.example.SimpleBankApp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SimpleBankApp.entity.Account;
import com.example.SimpleBankApp.entity.Transaction;
import com.example.SimpleBankApp.service.BankService;

@Controller
public class BankController {

    @Autowired
    private BankService bankService;

    // Display list of all accounts
    @GetMapping("/")
    public String viewAllAccounts(Model model) {
        List<Account> accountsList = bankService.getAllAccounts();
        model.addAttribute("accountList", accountsList);
        return "accounts"; // ✅ matches accounts.html
    }

    // Add a new account
    @PostMapping("/accounts/add")
    public String createAccount(@RequestParam String holderName, @RequestParam double initialBalance) {
        bankService.createAccount(holderName, initialBalance);
        return "redirect:/";
    }

    // Deposit to an account
    @PostMapping("/{id}/deposit")
    public String deposit(@PathVariable("id") Long accountId, @RequestParam("amount") double amount) {
        bankService.deposit(accountId, amount);
        return "redirect:/";
    }

    // Withdraw from an account
    @PostMapping("/{id}/withdraw")
    public String withdraw(@PathVariable("id") Long accountId, @RequestParam("amount") double amount) {
        bankService.withdraw(accountId, amount);
        return "redirect:/";
    }

    @GetMapping("/{id}/transactions")
    public String viewTransactionsByPath(
            @PathVariable("id") Long accountId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            Model model) {

        if (startDate == null) startDate = LocalDateTime.of(1900, 1, 1, 0, 0);
        if (endDate == null) endDate = LocalDateTime.now();

        List<Transaction> transactions = bankService.getTransactionHistory(accountId, type, startDate, endDate);
        model.addAttribute("transactions", transactions);
        model.addAttribute("accountId", accountId);

        return "transactions"; // must match transactions.html
    }



}
