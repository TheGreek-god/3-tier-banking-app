package com.example.bankapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private Double balance = 1000.0;
    
    @Column(nullable = false)
    private String accountType = "Savings";

    public Account() {}
    
    public Account(String username) {
        this.username = username;
        this.balance = 1000.0;
        this.accountType = "Savings";
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
    
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    
    // Business methods
    public void deposit(Double amount) {
        this.balance += amount;
    }
    
    public void withdraw(Double amount) {
        this.balance -= amount;
    }
    
    public boolean canWithdraw(Double amount) {
        return this.balance >= amount;
    }
}