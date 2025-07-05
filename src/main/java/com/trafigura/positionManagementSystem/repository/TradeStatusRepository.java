package com.trafigura.positionManagementSystem.repository;

import com.trafigura.positionManagementSystem.model.TradeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeStatusRepository extends JpaRepository<TradeStatus, Long> {
}