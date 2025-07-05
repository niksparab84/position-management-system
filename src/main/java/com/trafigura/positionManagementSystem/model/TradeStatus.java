package com.trafigura.positionManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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