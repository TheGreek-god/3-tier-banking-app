package com.example.bankapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String type; // DEPOSIT, WITHDRAW, TRANSFER
    
    @Column(nullable = false)
    private Double amount;
    
    private String description;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    private String recipientUsername; // For transfers

    // Constructors
    public Transaction() {
        this.timestamp = LocalDateTime.now();
    }
    
    public Transaction(String username, String type, Double amount, String description) {
        this();
        this.username = username;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }
    
    public Transaction(String username, String type, Double amount, String description, String recipientUsername) {
        this(username, type, amount, description);
        this.recipientUsername = recipientUsername;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    
    public String getRecipientUsername() { return recipientUsername; }
    public void setRecipientUsername(String recipientUsername) { this.recipientUsername = recipientUsername; }
}