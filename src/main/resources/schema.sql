-- DATABASE SCHEMA FOR EQUITIES POSITION MANAGEMENT SYSTEM
-- TRADE Table: To manage trades with versioning per security
CREATE TABLE TRADE (
    trade_id BIGINT NOT NULL,
    version INT NOT NULL,
    security_code VARCHAR(20) NOT NULL,
    quantity INT NOT NULL,
    buy_sell CHAR(1) NOT NULL, -- 'B' or 'S'
    last_action VARCHAR(10) NOT NULL, -- 'INSERT', 'UPDATE', 'CANCEL'
    last_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (trade_id, version)
);

ALTER TABLE TRADE ADD CONSTRAINT unique_trade_id UNIQUE (trade_id);

-- Helper table for generating new trade_id for TRADE table
CREATE TABLE TRADE_ID_GEN (
    trade_id BIGINT PRIMARY KEY AUTO_INCREMENT
);

-- TRADE_STATUS Table: To manage Latest version and status per trade
CREATE TABLE TRADE_STATUS (
    trade_id BIGINT PRIMARY KEY,
    latest_version INT NOT NULL,
    status VARCHAR(10) NOT NULL, -- 'ACTIVE', 'CANCELLED'
    FOREIGN KEY (trade_id) REFERENCES TRADE(trade_id)
);

-- H2 compatible Trigger to automatically manage trade_id and versioning
CREATE TRIGGER trg_trade_id_version
BEFORE INSERT ON TRADE
FOR EACH ROW
CALL "com.trafigura.positionManagementSystem.repository.triggers.TradeIdVersionTrigger";


-- TRANSACTION Table: To manage all transactions related to trades
CREATE TABLE TRANSACTION (
    transaction_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    trade_id BIGINT NOT NULL,
    version INT NOT NULL,
    security_code VARCHAR(20) NOT NULL,
    quantity INT NOT NULL,
    buy_sell CHAR(1) NOT NULL,
    action VARCHAR(10) NOT NULL, -- 'INSERT', 'UPDATE', 'CANCEL'
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (trade_id, version) REFERENCES TRADE(trade_id, version)
);

-- POSITION Table: To manage current positions
CREATE TABLE POSITION (
    position_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    security_code VARCHAR(20) NOT NULL UNIQUE,
    quantity INT NOT NULL
);

-- Enhanced Trigger: Checks TRADE_STATUS for ACTIVE trade
-- Commented out as it is not directly supported in H2
--/*
--BEGIN
--CREATE TRIGGER trg_trade_id_version
--BEFORE INSERT ON TRADE
--FOR EACH ROW
--BEGIN
--    DECLARE existing_trade_id BIGINT;
--    -- Find active trade_id for the security_code from TRADE_STATUS
--    SELECT t.trade_id INTO existing_trade_id
--    FROM TRADE t
--    JOIN TRADE_STATUS ts ON t.trade_id = ts.trade_id
--    WHERE t.security_code = NEW.security_code
--      AND ts.status = 'ACTIVE'
--    ORDER BY t.version DESC LIMIT 1;
--
--    IF existing_trade_id IS NOT NULL THEN
--        -- Reuse trade_id, increment version
--        SET NEW.trade_id = existing_trade_id;
--        SET NEW.version = IFNULL(
--            (SELECT MAX(version) + 1 FROM TRADE WHERE trade_id = existing_trade_id), 1
--        );
--    ELSE
--        -- Generate new trade_id
--        INSERT INTO TRADE_ID_GEN VALUES (NULL);
--        SET NEW.trade_id = LAST_INSERT_ID();
--        SET NEW.version = 1;
--    END IF;
--END;
--*/