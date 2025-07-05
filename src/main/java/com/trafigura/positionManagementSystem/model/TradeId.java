package com.trafigura.positionManagementSystem.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class TradeId implements Serializable {
    private Long tradeId;
    private Integer version;
}