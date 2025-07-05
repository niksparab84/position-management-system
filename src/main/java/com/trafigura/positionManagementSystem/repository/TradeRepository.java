package com.trafigura.positionManagementSystem.repository;

import com.trafigura.positionManagementSystem.model.Trade;
import com.trafigura.positionManagementSystem.model.TradeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, TradeId> {
    // This interface will automatically provide CRUD operations for Trade entities
    // The JpaRepository interface provides methods like save, findById, findAll, deleteById, etc.
    // No additional methods are needed unless specific queries are required

}