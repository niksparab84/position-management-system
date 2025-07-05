package com.trafigura.positionManagementSystem.repository;

import com.trafigura.positionManagementSystem.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {

}