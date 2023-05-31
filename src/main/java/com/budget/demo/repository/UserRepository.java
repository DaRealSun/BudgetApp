package com.budget.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.budget.demo.data.Transaction;
import com.budget.demo.data.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
