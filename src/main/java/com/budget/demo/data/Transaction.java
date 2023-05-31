package com.budget.demo.data;


import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private TransactionType type; // income or expense
	private String description;
	private double amount;
	private LocalDate date;
	
	@ManyToOne
	private User user;
	
	//Constructors, getters and setter...
}
