package com.trafigura.positionManagementSystem.repository.triggers;

import org.h2.api.Trigger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;

public class TradeIdVersionTrigger implements Trigger {
    @Override
    public void init(Connection conn, String schemaName, String triggerName,
                     String tableName, boolean before, int type) {}

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
        String securityCode = (String) newRow[2];
        Long existingTradeId = null;
        Integer latestVersion = null;

        // Find active trade_id for the security_code from TRADE_STATUS
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT t.trade_id, MAX(t.version) " +
                        "FROM TRADE t JOIN TRADE_STATUS ts ON t.trade_id = ts.trade_id " +
                        "WHERE t.security_code = ? AND ts.status = 'ACTIVE' " +
                        "GROUP BY t.trade_id ORDER BY MAX(t.version) DESC LIMIT 1")) {
            ps.setString(1, securityCode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    existingTradeId = rs.getLong(1);
                    latestVersion = rs.getInt(2);
                }
            }
        }

        if (existingTradeId != null) {
            // Reuse trade_id, increment version
            newRow[0] = existingTradeId;
            newRow[1] = latestVersion + 1;
        } else {
            // Generate new trade_id
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO TRADE_ID_GEN VALUES (NULL)", Statement.RETURN_GENERATED_KEYS)) {
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        newRow[0] = rs.getLong(1);
                    }
                }
            }
            newRow[1] = 1;
        }
    }

    @Override
    public void close() {}

    @Override
    public void remove() {}
}