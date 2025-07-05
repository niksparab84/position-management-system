package com.trafigura.positionManagementSystem.repository;

import com.trafigura.positionManagementSystem.model.TradeIdGen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeIdGenRepository extends JpaRepository<TradeIdGen, Long> {
}
