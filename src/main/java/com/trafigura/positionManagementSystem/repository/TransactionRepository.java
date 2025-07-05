package com.trafigura.positionManagementSystem.repository;

import com.trafigura.positionManagementSystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
