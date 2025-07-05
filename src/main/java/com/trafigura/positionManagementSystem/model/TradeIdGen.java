package com.trafigura.positionManagementSystem.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "TRADE_ID_GEN")
public class TradeIdGen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    private Long tradeId;
}