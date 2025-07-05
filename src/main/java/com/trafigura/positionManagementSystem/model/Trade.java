package com.trafigura.positionManagementSystem.model;

import lombok.Data;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "TRADE", uniqueConstraints = @UniqueConstraint(columnNames = {"trade_id", "version"}))
public class Trade {
    @Id
    @Column(name = "trade_id")
    private Long tradeId;

    @Id
    @Column(name = "version")
    private Integer version;

    @Column(name = "security_code", nullable = false, length = 20)
    private String securityCode;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "buy_sell", nullable = false, length = 1)
    private String buySell;

    @Column(name = "last_action", nullable = false, length = 10)
    private String lastAction;

    @Column(name = "last_updated", nullable = false)
    private Timestamp lastUpdated;
}