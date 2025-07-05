package com.trafigura.positionManagementSystem.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "TRADE_STATUS")
public class TradeStatus {
    @Id
    @Column(name = "trade_id")
    private Long tradeId;

    @Column(name = "latest_version", nullable = false)
    private Integer latestVersion;

    @Column(name = "status", nullable = false, length = 10)
    private String status;
}