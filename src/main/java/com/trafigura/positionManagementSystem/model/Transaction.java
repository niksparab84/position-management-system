package com.trafigura.positionManagementSystem.model;

import lombok.Data;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "trade_id", nullable = false)
    private Long tradeId;

    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "security_code", nullable = false, length = 20)
    private String securityCode;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "buy_sell", nullable = false, length = 1)
    private String buySell;

    @Column(name = "action", nullable = false, length = 10)
    private String action;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;
}