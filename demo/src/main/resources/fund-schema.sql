CREATE TABLE FUND(
    ID INT  NOT NULL PRIMARY KEY,
    FUND_CODE VARCHAR(255),
    FUND_NAME VARCHAR(255),
    AMOUNT DOUBLE,
);

INSERT INTO FUND VALUES (1, 'fund code 1', 'fund name 1', 10000.10);
INSERT INTO FUND VALUES (2, 'fund code 2', 'fund name 2', 20000.20);
INSERT INTO FUND VALUES (3, 'fund code 3','fund name 1',  30000.30);
INSERT INTO FUND VALUES (4, 'fund code 4', 'fund name 1', 40000.40);
INSERT INTO FUND VALUES (5, 'fund code 5', 'fund name 1', 50000.50);
