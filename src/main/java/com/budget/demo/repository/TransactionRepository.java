package com.budget.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.budget.demo.data.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
