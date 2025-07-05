package com.trafigura.positionManagementSystem.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "POSITION")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long positionId;

    @Column(name = "security_code", nullable = false, unique = true, length = 20)
    private String securityCode;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}