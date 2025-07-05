package com.trafigura.positionManagementSystem.repository;

import com.trafigura.positionManagementSystem.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {

}