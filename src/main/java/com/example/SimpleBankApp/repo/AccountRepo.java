package com.example.SimpleBankApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SimpleBankApp.entity.Account;

public interface AccountRepo extends JpaRepository<Account,Long>{

}
